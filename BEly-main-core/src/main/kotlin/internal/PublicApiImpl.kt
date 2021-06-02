package com.elouyi.bely.internal

import com.elouyi.bely.publicapi.BiliApiUrl
import com.elouyi.bely.publicapi.PublicApi
import com.elouyi.bely.publicapi.response.VideoResponse
import com.elouyi.bely.utils.ElyLogger
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.*

internal object PublicApiImpl : PublicApi {

    override val coroutineContext = CoroutineName(
        "PublicApi" +
                SupervisorJob() +
                CoroutineExceptionHandler { _, throwable ->
                    logger.e("PublicApi:",throwable)
                } +
                Dispatchers.IO
    )

    override val logger: ElyLogger = ElyLogger("PublicApi")

    override val client: HttpClient = HttpClient(CIO) {
        BrowserUserAgent()
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    override fun videoInfoAsync(av: Long): Deferred<VideoResponse> = async {
        client.get(BiliApiUrl.videoInfo(av))
    }

    override fun videoInfoAsync(bv: String): Deferred<VideoResponse> = async {
        client.get(BiliApiUrl.videoInfo(bv))
    }
}