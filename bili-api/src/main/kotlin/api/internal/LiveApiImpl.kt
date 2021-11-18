package com.elouyi.bely.api.internal

import com.elouyi.bely.api.LiveApi
import com.elouyi.bely.data.LiveEvent
import com.elouyi.bely.data.internal.repository.LiveRepository
import com.elouyi.bely.data.response.live.LiveDanmuInfoResponse
import kotlinx.coroutines.channels.ReceiveChannel


internal class LiveApiImpl(
    private val repository: LiveRepository
) : LiveApi {

    override suspend fun getDanmuInfo(roomId: Int): LiveDanmuInfoResponse = repository.getDanmuInfo(roomId)

    override suspend fun liveRoomEventChannel(roomId: Int): ReceiveChannel<LiveEvent> {
        return repository.getLiveEvent(roomId)
    }
}