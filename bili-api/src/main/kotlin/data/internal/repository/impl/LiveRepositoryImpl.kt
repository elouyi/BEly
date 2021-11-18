package com.elouyi.bely.data.internal.repository.impl

import com.elouyi.bely.data.LiveEvent
import com.elouyi.bely.data.internal.repository.LiveRepository
import com.elouyi.bely.data.response.live.LiveDanmuInfoResponse
import com.elouyi.bely.data.response.live.LiveRoomPlayInfoResponse
import com.elouyi.bely.data.url.LiveUrl
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch


internal class LiveRepositoryImpl : LiveRepository {

    override val client: HttpClient = com.elouyi.bely.utils.internal.newClient()

    override suspend fun getDanmuInfo(roomId: Int): LiveDanmuInfoResponse = client.get(LiveUrl.liveDanmuInfo(roomId))

    override suspend fun getRoomPlayInfo(roomId: Int): LiveRoomPlayInfoResponse =
        client.get(LiveUrl.liveRoomPlay(roomId))

    override fun getLiveEventByTrueRoomId(roomId: Int): ReceiveChannel<LiveEvent> {

        val channel = Channel<LiveEvent>(10)
        client.launch {
            val info = getDanmuInfo(roomId).data ?: throw CancellationException()
            val hosts = info.host_list.takeUnless { it.isEmpty() }?.get(0) ?: throw CancellationException()
            client.wss(
                host = hosts.host,
                port = hosts.wss_port,
                path = "/sub"
            ) {
                channel.send(LiveEvent.Unknown(incoming.receive().data))
            }
        }.invokeOnCompletion {
            channel.close(it)
        }
        return channel
    }
}