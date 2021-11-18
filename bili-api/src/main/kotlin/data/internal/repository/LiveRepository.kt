package com.elouyi.bely.data.internal.repository

import com.elouyi.bely.data.LiveEvent
import com.elouyi.bely.data.response.live.LiveDanmuInfoResponse
import com.elouyi.bely.data.response.live.LiveRoomPlayInfoResponse
import kotlinx.coroutines.channels.ReceiveChannel


internal interface LiveRepository : RepositoryBase {

    suspend fun getDanmuInfo(roomId: Int): LiveDanmuInfoResponse

    suspend fun getRoomPlayInfo(roomId: Int): LiveRoomPlayInfoResponse


    suspend fun getLiveEvent(roomId: Int): ReceiveChannel<LiveEvent>

}
