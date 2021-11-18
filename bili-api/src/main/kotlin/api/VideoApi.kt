package com.elouyi.bely.api

import com.elouyi.bely.data.response.video.VideoDataResponse


public interface VideoApi : BiliApi {

    public suspend fun videoInfo(av: Int): VideoDataResponse

    public suspend fun videoInfo(bv: String): VideoDataResponse

}