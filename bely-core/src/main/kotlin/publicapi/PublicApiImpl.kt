package com.elouyi.bely.publicapi

import com.elouyi.bely.apiutils.liveRoomEventChannel
import com.elouyi.bely.biliapi.data.live.LiveDanmuInfoResponse
import com.elouyi.bely.biliapi.data.live.LiveRoomEvent
import com.elouyi.bely.biliapi.data.live.RoomPlayInfoResponse
import com.elouyi.bely.biliapi.data.personal.CheckNickNameResponse
import com.elouyi.bely.biliapi.data.relation.RelationFollowerResponse
import com.elouyi.bely.biliapi.data.relation.RelationFollowingResponse
import com.elouyi.bely.publicapi.response.AccInfoResponse
import com.elouyi.bely.publicapi.response.MasterPieceResponse
import com.elouyi.bely.publicapi.response.RelationResponse
import com.elouyi.bely.publicapi.response.VideoResponse
import com.elouyi.bely.utils.ElyLogger
import com.elouyi.bely.utils.annotation.TodoApi
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

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

    override fun relationFollowersAsync(
        vmid: Long,
        access_key: String?,
        ps: Int,
        pn: Int
    ): Deferred<RelationFollowerResponse> = async {
        client.get(PublicApiUrl.relationFollowers(vmid,access_key, ps, pn))
    }

    override fun relationFollowingsAsync(
        vmid: Long,
        access_key: String?,
        ps: Int,
        pn: Int
    ): Deferred<RelationFollowingResponse> = async {
        client.get(PublicApiUrl.relationFollowings(vmid, access_key, ps, pn))
    }

    override fun checkNickNameAsync(nickName: String): Deferred<CheckNickNameResponse> = async {
        client.get(PublicApiUrl.checkNickName(nickName))
    }

    @TodoApi
    override fun webDanmukuAsync(type: Int, oid: Int, segment_index: Int): Deferred<Nothing> = async {
        error("todo")
        //client.get(PublicApiUrl.webDanmuku(type, oid, segment_index))
    }

    override fun getRoomPlayInfoAsync(roomId: Int): Deferred<RoomPlayInfoResponse> = async {
        client.get(PublicApiUrl.getRoomPlayInfo(roomId))
    }

    override fun liveDanmuInfoAsync(id: Long): Deferred<LiveDanmuInfoResponse> = async {
        client.get(PublicApiUrl.liveDanmuInfo(id))
    }

    override suspend fun getLiveRoomEventChannel(roomId: Int): Channel<LiveRoomEvent> {
        return liveRoomEventChannel(roomId)
    }
}