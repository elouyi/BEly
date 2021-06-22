package com.elouyi.bely.apiutils

import com.elouyi.bely.biliapi.data.live.LiveRoomEvent
import com.elouyi.bely.publicapi.PublicApiImpl
import com.elouyi.bely.utils.ElyLogger
import com.elouyi.bely.utils.base64Bytes
import com.elouyi.bely.utils.toHexByte
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.nio.ByteBuffer
import java.util.concurrent.Executors

private val wedSocketDispatcher = Executors.newFixedThreadPool(10).asCoroutineDispatcher()
private val scope = CoroutineScope(
    CoroutineName("wedSocket") +
            wedSocketDispatcher
)
private val logger = ElyLogger("websocket")

private val heartBeatBytes = "AAAAHwAQAAEAAAACAAAAAVtvYmplY3QgT2JqZWN0XQ==".base64Bytes()

suspend fun liveRoomEventChannel(roomId: Int): Channel<LiveRoomEvent> {
    val id = getRoomId(roomId)
    logger.v("获取到房间号 $roomId -> $id")
    val liveDanmuInfo = PublicApiImpl.liveDanmuInfoAsync(id).await()
    logger.v("获取到直播间信息")
    val reqInfo = requestInfo(id,liveDanmuInfo.data.token)
    val hostInfo = liveDanmuInfo.data.host_list.last()
    val h = pack(reqInfo)
    val url = "wss://${hostInfo.host}:${hostInfo.wss_port}/sub"
    val info = LiveRoomSocketInfo(
        roomId,
        id,
        h,
        heartBeatBytes,
        url
    )
    logger.v("开始尝试连接ws $url")
    return withContext(Dispatchers.IO) {
        LiveRoomWebsocketClient(info).apply {
            connect()
        }
    }
}

class LiveRoomSocketInfo(
    val shortId: Int,
    val roomId: Long,
    val h: ByteArray,
    val heartBeat: ByteArray,
    val url: String,
)

private fun requestInfo(roomId: Long,token: String): ByteArray {
    return buildJsonObject {
        put("uid",0)
        put("roomid",roomId)
        put("protover",2)
        put("platform","web")
        put("type",2)
        put("key",token)
    }.toString().toByteArray()
}

private suspend fun getRoomId(roomid: Int): Long {
    val roomPlayInfo = PublicApiImpl.getRoomPlayInfoAsync(roomid).await()
    if (roomPlayInfo.code != 0) {
        logger.e("请求直播间信息失败:${roomPlayInfo.message}")
        throw Exception("请求直播间信息失败")
    }
    return roomPlayInfo.data.room_id
}

internal fun pack(bytes: ByteArray): ByteArray {
    val h = ByteArray(16)
    h[0] = (16 + bytes.size).toString(16).toHexByte()
    h[4] = "10".toHexByte()
    h[6] = "01".toHexByte()
    h[8] = "07".toHexByte()
    h[12] = "01".toHexByte()
    val bu = ByteBuffer.allocate(16 + bytes.size)
        .putInt(0,16 + bytes.size)
        .putShort(4,16)
        .putShort(6,1)
        .putInt(8,7)
        .putInt(12,1)
    val h2 = ByteArray(16) {
        bu[it]
    }
    return h2 + bytes
}

