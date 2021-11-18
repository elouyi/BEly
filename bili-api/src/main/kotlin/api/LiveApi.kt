package com.elouyi.bely.api

import com.elouyi.bely.data.LiveEvent
import com.elouyi.bely.data.response.live.LiveDanmuInfoResponse
import kotlinx.coroutines.channels.ReceiveChannel


public interface LiveApi : BiliApi {

    public suspend fun getDanmuInfo(roomId: Int): LiveDanmuInfoResponse

    public suspend fun liveRoomEventChannel(roomId: Int): ReceiveChannel<LiveEvent>
}