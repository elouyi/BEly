package com.elouyi.bely.publicapi

import com.elouyi.bely.publicapi.response.VideoResponse
import com.elouyi.bely.utils.ElyLogger
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

interface PublicApi : CoroutineScope {

    val logger: ElyLogger

    val client: HttpClient

    fun videoInfoAsync(av: Long): Deferred<VideoResponse>

    fun videoInfoAsync(bv: String): Deferred<VideoResponse>
}