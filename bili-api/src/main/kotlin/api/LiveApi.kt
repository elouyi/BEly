package com.elouyi.bely.api

import com.elouyi.bely.data.response.live.LiveDanmuInfoResponse


public interface LiveApi : BiliApi {

    public suspend fun getDanmuInfo(roomId: Int): LiveDanmuInfoResponse
}