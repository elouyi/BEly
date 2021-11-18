package com.elouyi.bely.data.internal.repository

import com.elouyi.bely.data.response.video.VideoDataResponse


internal interface VideoRepository : RepositoryBase {

    suspend fun videoInfo(av: Int): VideoDataResponse

    suspend fun videoInfo(bv: String): VideoDataResponse
}
