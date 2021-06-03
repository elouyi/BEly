package com.elouyi.bely.publicapi

import com.elouyi.bely.publicapi.response.AccInfoResponse
import com.elouyi.bely.publicapi.response.MasterPieceResponse
import com.elouyi.bely.publicapi.response.RelationResponse
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
        client.get(PublicApiUrl.videoInfo(av))
    }

    override fun videoInfoAsync(bv: String): Deferred<VideoResponse> = async {
        client.get(PublicApiUrl.videoInfo(bv))
    }

    override fun relationAsync(uid: Long): Deferred<RelationResponse> = async {
        client.get(PublicApiUrl.relation(uid))
    }

    override fun masterPieceAsync(uid: Long): Deferred<MasterPieceResponse> = async {
        client.get(PublicApiUrl.masterPiece(uid))
    }

    override fun accInfoAsync(uid: Long): Deferred<AccInfoResponse> = async {
        client.get(PublicApiUrl.accInfo(uid))
    }
}