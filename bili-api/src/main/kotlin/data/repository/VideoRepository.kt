package com.elouyi.bely.data.repository

import com.elouyi.bely.data.response.VideoDataResponse


public interface VideoRepository {

    public suspend fun videoInfo(av: Int): VideoDataResponse

    public suspend fun videoInfo(bv: String): VideoDataResponse
}
