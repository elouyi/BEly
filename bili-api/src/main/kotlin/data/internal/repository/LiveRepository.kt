package com.elouyi.bely.data.internal.repository

import com.elouyi.bely.data.LiveEvent
import com.elouyi.bely.data.response.live.LiveDanmuInfoResponse
import com.elouyi.bely.data.response.live.LiveRoomPlayInfoResponse
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow


internal interface LiveRepository : RepositoryBase {

    suspend fun getDanmuInfo(roomId: Int): LiveDanmuInfoResponse

    suspend fun getRoomPlayInfo(roomId: Int): LiveRoomPlayInfoResponse

    fun getLiveEventByTrueRoomId(roomId: Int): ReceiveChannel<LiveEvent>

    suspend fun getLiveEvent(roomId: Int): ReceiveChannel<LiveEvent> {
        val roomInfo = getRoomPlayInfo(roomId).data ?: error("failed to get true room id")
        return getLiveEventByTrueRoomId(roomInfo.room_id)
    }

}