package com.elouyi.bely.data.internal.repository.impl

import com.elouyi.bely.data.LiveEvent
import com.elouyi.bely.data.internal.BiliHead
import com.elouyi.bely.data.internal.repository.BElyRepository
import com.elouyi.bely.data.internal.repository.LiveRepository
import com.elouyi.bely.data.model.live.LiveDanmuExtra
import com.elouyi.bely.data.response.live.LiveDanmuInfoResponse
import com.elouyi.bely.data.response.live.LiveRoomPlayInfoResponse
import com.elouyi.bely.data.url.LiveUrl
import com.elouyi.bely.event.internal.ElyReceiveChannel
import com.elouyi.bely.utils.cipher.decompress
import com.elouyi.bely.utils.internal.GlobalCEH
import com.elouyi.bely.utils.internal.newClient
import com.elouyi.bely.utils.readBiliHead
import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.cio.websocket.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*


internal class LiveRepositoryImpl : LiveRepository {

    override val client: HttpClient = newClient()

    override suspend fun getDanmuInfo(roomId: Int): LiveDanmuInfoResponse = client.get(LiveUrl.liveDanmuInfo(roomId))

    override suspend fun getRoomPlayInfo(roomId: Int): LiveRoomPlayInfoResponse =
        client.get(LiveUrl.liveRoomPlay(roomId))

    override suspend fun getLiveEvent(roomId: Int): ReceiveChannel<LiveEvent> {
        val info = getDanmuInfo(roomId).data ?: throw CancellationException()
        val hosts = info.host_list.takeUnless { it.isEmpty() }?.lastOrNull() ?: throw CancellationException()
        val trueRoomId = BElyRepository.live.getRoomPlayInfo(roomId).data?.room_id ?: throw CancellationException()
        val channel = Channel<LiveEvent>(10)
        val data = buildJsonObject {
            put("uid",0)
            put("roomid", trueRoomId)
            put("protover", 2)
            put("platform", "web")
            put("type",2)
            put("key",info.token)
        }.toString().toByteArray()
        val b = buildPacket {
            val size = 16 + data.size
            writeInt(size) // 封包总大小
            writeShort(0x10) // 头部大小
            writeShort(1) // 协议版本
            writeInt(7) // 类型
            writeInt(1)
            writePacket(ByteReadPacket(data))
        }
        val job = client.launch {
            client.wss(
                host = hosts.host,
                port = hosts.wss_port,
                path = "/sub"
            ) {
                val byte = b.readBytes()
                outgoing.send(Frame.Binary(true, byte))
                launch {
                    delay(5000)
                    while (isActive) {
                        outgoing.send(Frame.Binary(true, heartbeat))
                        delay(30_000)
                    }
                }
                while (isActive) {
                    val frame = incoming.receive()
                    val eventData = frame.data
                    launch {
                        handleLiveEventData(eventData).forEach {
                            channel.send(it)
                        }
                    }
                }
            }
        }
        job.invokeOnCompletion {
            it?.printStackTrace()
        }
        channel.invokeOnClose {
            job.cancel(it as? CancellationException)
            it?.printStackTrace()
        }
        return ElyReceiveChannel(channel)
    }


    companion object {
        val heartbeat = byteArrayOf(
            0,0,0,0x1f,
            0,0x10,0,0x1,
            0,0,0,0x2,
            0,0,0,0x1,
            0x5b,0x6f,0x62,0x6a,
            0x65,0x63,0x74,0x20,
            0x4f,0x62,0x6a,0x65,
            0x63,0x74,0x5d

        )

        private suspend fun handleLiveEventData(data: ByteArray): List<LiveEvent> = withContext(Dispatchers.IO) {
            if (data.size <= 16) return@withContext emptyList()
            val result = mutableListOf<LiveEvent>()
            val bytePack = ByteReadPacket(data)
            val head = bytePack.readBiliHead()
            val body = bytePack.readBytes((head.totalLen - head.headLen).toInt())
            result += handleLiveEventBody(head, body)
            return@withContext if (bytePack.remaining > 0) result + handleLiveEventData(bytePack.readBytes()) else result
        }

        private fun handleLiveEventBody(head: BiliHead, data: ByteArray): List<LiveEvent> {
            val result = mutableListOf<LiveEvent>()
            val bytePack = ByteReadPacket(data)
            when(head.type) {
                3 -> {
                    result.add(LiveEvent.HeartBeat(bytePack.readInt()))
                    bytePack.readBytes()
                }
                5 -> {
                    val body = bytePack.readBytes((head.totalLen - 0x10).toInt())
                    result += handleLiveCMDEvent(head, body)
                }
                else -> {
                    val body = bytePack.readBytes((head.totalLen - 0x10).toInt())
                    result.add(LiveEvent.Unknown(body))
                }
            }
            return if (bytePack.remaining > 0) result + handleLiveEventBody(bytePack.readBiliHead(), bytePack.readBytes())
            else result
        }

        private fun handleSingleLiveEvent(head: BiliHead, data: ByteArray): LiveEvent {
            val bytePack = ByteReadPacket(data)
            return when(head.type) {
                3 -> {
                    LiveEvent.HeartBeat(bytePack.readInt())
                }
                5 -> {
                    TODO()
                    //handleLiveCMDEvent(head, data)
                }
                else -> LiveEvent.Unknown(data)
            }
        }

        private fun handleLiveCMDEvent(head: BiliHead, data: ByteArray): List<LiveEvent> {
            val result = mutableListOf<LiveEvent>()
            val strData: String
            when(head.protover) {
                0 -> {
                    strData = String(data)
                }
                2 -> {
                    val decompressed = decompress(data).takeIf { it.isNotEmpty() } ?: return listOf(LiveEvent.Unknown(data))
                    val bytePack = ByteReadPacket(decompressed)
                    val biliHead = bytePack.readBiliHead()
                    val body = bytePack.readBytes((biliHead.totalLen - biliHead.headLen).toInt())
                    if (bytePack.remaining > 16 ) {
                        result += handleLiveEventBody(bytePack.readBiliHead(), bytePack.readBytes())
                    }
                    strData = String(body)
                }
                else -> return result
            }
            val cmd = strData
                .substringAfter("cmd\":")
                .substringAfter("\"")
                .substringBefore("\"")
            when (cmd) {
                "DANMU_MSG" -> {
                    val info = strData
                        .substringAfter("info\":")
                        .substringBeforeLast("}")
                    try {
                        result.add(handleLiveDanmuEvent(info))
                    } catch (e: Exception) {
                        result.add(LiveEvent.Unknown(data))
                    }
                }
                else -> result.add(LiveEvent.Unknown(data))
            }
            return result
        }

        private fun handleLiveDanmuEvent(info: String): LiveEvent {
            val extraStr = info
                .trim()
                .substringAfter("\"extra\":\"")
                .substringBefore("\"}],")
                .replace("\\","")
            val extra = try {
                Json.decodeFromString<LiveDanmuExtra>(extraStr)
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
            return LiveEvent.Danmu(extra.content)
        }
    }
}
