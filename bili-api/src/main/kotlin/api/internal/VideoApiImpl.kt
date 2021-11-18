package com.elouyi.bely.api.internal

import com.elouyi.bely.api.VideoApi
import com.elouyi.bely.data.internal.repository.BElyRepository
import com.elouyi.bely.data.response.video.VideoDataResponse


internal object VideoApiImpl : VideoApi {

    private val repository = BElyRepository.video

    override suspend fun videoInfo(av: Int): VideoDataResponse = repository.videoInfo(av)

    override suspend fun videoInfo(bv: String): VideoDataResponse = repository.videoInfo(bv)

}