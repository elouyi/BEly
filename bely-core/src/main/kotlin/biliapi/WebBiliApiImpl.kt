package com.elouyi.bely.biliapi

import com.elouyi.bely.biliapi.data.message.*
import com.elouyi.bely.biliapi.data.personal.*
import com.elouyi.bely.biliapi.data.relation.*
import com.elouyi.bely.biliapi.data.search.SearchAllWebResponse
import com.elouyi.bely.contact.WebBiliBotImpl
import com.elouyi.bely.publicapi.PublicApi
import com.elouyi.bely.publicapi.PublicApiImpl
import com.elouyi.bely.publicapi.response.AccInfoResponse
import com.elouyi.bely.publicapi.response.MasterPieceResponse
import com.elouyi.bely.publicapi.response.RelationResponse
import com.elouyi.bely.publicapi.response.VideoResponse
import com.elouyi.bely.security.utils.UserCookieCache
import com.elouyi.bely.security.utils.withUserCookies
import com.elouyi.bely.utils.ElyLogger
import com.elouyi.bely.utils.getWithCookies
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.coroutines.*

internal class WebBiliApiImpl(
    override val bot: WebBiliBotImpl
) : WebBiliApi, PublicApi by PublicApiImpl {

    override val client: HttpClient = super.client

    override val logger: ElyLogger = super.logger

    override val coroutineContext = CoroutineName(
        "${bot.uid} biliApi" +
                SupervisorJob() +
                CoroutineExceptionHandler { _, throwable ->
                    logger.e("WebApiApi:",throwable)
                } +
                Dispatchers.IO
    )

    override fun getAccInfoAsync(): Deferred<AccountInfoResponse> = async {
        if (bot.cookies.isExpires) {
            bot.logger.w("cookies 过期，请尝试重新登录")
            // TODO: 2021/6/5 登录提醒
        }
        bot.client.get(BiliApiUrl.account()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun dailyRewardAsync(): Deferred<RewardResponse> = async {
        bot.client.get(BiliApiUrl.reward()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun dailyExpAsync(): Deferred<ExpResponse> = async {
        bot.client.get(BiliApiUrl.exp()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun vipInfoAsync(): Deferred<VipInfoResponse> = async {
        bot.client.get(BiliApiUrl.vipInfo()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun accountSecurityAsync(): Deferred<AccountSecurityResponse> = async {
        bot.client.get(BiliApiUrl.accountSecurity()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun realNameStatusAsync(): Deferred<RealNameStatusResponse> = async {
        bot.client.get(BiliApiUrl.realNameStatus()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun realNameApplyStatusAsync(): Deferred<RealNameApplyStatusResponse> = async {
        bot.client.get(BiliApiUrl.realNameApplyStatus()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun coinLogAsync(): Deferred<CoinLogResponse> = async {
        bot.client.get(BiliApiUrl.coinLog()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun updateSignAsync(sign: String): Deferred<UpdateSignResponse> = async {
        bot.client.submitForm(
            url = BiliApiUrl.updateSign(),
            formParameters = Parameters.build {
                append("user_sign",sign)
                append("csrf",bot.cookies.bili_jct)
            }
        ) {
            withUserCookies(bot.cookies)
        }
    }

    override suspend fun exitLogin() {
        bot.client.get<String>(BiliApiUrl.exitLogin()) {
            withUserCookies(bot.cookies)
        }
        UserCookieCache.deleteCookies(bot.cookies)
    }

    override fun navDataAsync(): Deferred<NavDataResponse> = async {
        bot.client.get(BiliApiUrl.navData()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun navStatAsync(): Deferred<NavStatResponse> = async {
        bot.client.get(BiliApiUrl.navStat()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun getCoinAsync(): Deferred<GetCoinResponse> = async {
        bot.client.get(BiliApiUrl.getCoin()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun unreadMessageAsync(): Deferred<UnreadMessageResponse>  = async {
        bot.client.get(BiliApiUrl.unreadMessage()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun unreadSingleAsync(): Deferred<UnreadSingleResponse> = async {
        bot.client.get(BiliApiUrl.unreadSingle()) {
            withUserCookies(bot.cookies)
        }
    }

    override fun sendMessageAsync(
        msg: String,
        target: Long,
        type: SendMessageData.MsgType
    ): Deferred<SendMessageResponse> = async {
        when(type) {
            SendMessageData.MsgType.TEXT -> {
                bot.client.submitForm(
                    url = BiliApiUrl.sendMessageWeb(),
                    formParameters = Parameters.build {
                        append("msg[sender_uid]",bot.uid.toString())
                        append("msg[receiver_id]",target.toString())
                        append("msg[receiver_type]","1")
                        append("msg[msg_type]","1")
                        append("msg[msg_status]","0")
                        append("msg[content]","{\"content\":\"$msg\"}")
                        append("msg[timestamp]",(System.currentTimeMillis() / 1000).toString())
                        append("msg[new_face_version]","0")
                        append("msg[dev_id]","759C7FAF-5EA8-4BCB-821C-B91F53143E88")
                        append("from_firework","0")
                        append("build","0")
                        append("mobi_app","web")
                        append("csrf_token",bot.cookies.bili_jct)
                        append("csrf",bot.cookies.bili_jct)
                    }
                ) {
                    withUserCookies(bot.cookies)
                }
            }
            SendMessageData.MsgType.PICTURE -> {
                bot.client.submitForm(
                    url = BiliApiUrl.sendMessageWeb(),
                    formParameters = Parameters.build {
                        append("msg[sender_uid]",bot.uid.toString())
                        append("msg[receiver_id]",target.toString())
                        append("msg[receiver_type]","1")
                        append("msg[msg_type]","2")
                        append("msg[msg_status]","0")
                        append("msg[content]", buildPictureMsg(msg))
                        append("msg[timestamp]",(System.currentTimeMillis() / 1000).toString())
                        append("msg[new_face_version]","0")
                        append("msg[dev_id]","759C7FAF-5EA8-4BCB-821C-B91F53143E88")
                        append("from_firework","0")
                        append("build","0")
                        append("mobi_app","web")
                        append("csrf_token",bot.cookies.bili_jct)
                        append("csrf",bot.cookies.bili_jct)
                    }
                ) {
                    withUserCookies(bot.cookies)
                }
            }
        }
    }

    override suspend fun dailyExp(): ExpResponse {
        return super.dailyExp()
    }

    override fun videoInfoAsync(av: Long): Deferred<VideoResponse> = async {
        client.get(BiliApiUrl.videoInfo(av)) {
            withUserCookies(bot.cookies)
        }
    }

    override fun videoInfoAsync(bv: String): Deferred<VideoResponse> = async {
        getWithCookies(BiliApiUrl.videoInfo(bv))
    }

    override fun relationAsync(uid: Long): Deferred<RelationResponse> = async {
        getWithCookies(BiliApiUrl.relation(uid))
    }

    override fun masterPieceAsync(uid: Long): Deferred<MasterPieceResponse> = async {
        getWithCookies(BiliApiUrl.masterPiece(uid))
    }

    override fun accInfoAsync(uid: Long): Deferred<AccInfoResponse> = async {
        getWithCookies(BiliApiUrl.accInfo(uid))
    }

    override fun relationFollowersAsync(
        vmid: Long,
        access_key: String?,
        ps: Int,
        pn: Int
    ): Deferred<RelationFollowerResponse> = async {
        getWithCookies(BiliApiUrl.relationFollowers(vmid, access_key, ps, pn))
    }

    override fun relationFollowingsAsync(
        vmid: Long,
        access_key: String?,
        ps: Int,
        pn: Int
    ): Deferred<RelationFollowingResponse> = async {
        getWithCookies(BiliApiUrl.relationFollowings(vmid, access_key, ps, pn))
    }

    override fun relationFollowingsSearchAsync(
        mid: Long,
        name: String,
        access_key: String?,
        ps: Int,
        pn: Int
    ): Deferred<RelationFollowingsSearchResponse> = async {
        getWithCookies(BiliApiUrl.relationFollowingSearch(mid,name, access_key, ps, pn))
    }

    override fun relationSameFollowingsAsync(
        vmid: Long,
        access_key: String?,
        ps: Int,
        pn: Int
    ): Deferred<RelationSameFollowingsResponse> = async {
        getWithCookies(BiliApiUrl.relationSameFollowings(vmid, access_key, ps, pn))
    }

    override fun relationWhispersAsync(
        access_key: String?,
        ps: Int,
        pn: Int
    ): Deferred<RelationWhispersResponse> = async {
        getWithCookies(BiliApiUrl.relationWhispers(access_key, ps, pn))
    }

    override fun relationBlacksAsync(
        access_key: String?,
        ps: Int,
        pn: Int
    ): Deferred<RelationBlacksResponse> = async {
        getWithCookies(BiliApiUrl.relationBlacks(access_key, ps, pn))
    }

    override fun relationSubscribeAsync(
        fid: Long,
        re_src: RelationSubscribeSrc,
        access_key: String?
    ): Deferred<RelationModifyResponse> = async {
        client.submitForm(
            url = BiliApiUrl.relationModify(),
            formParameters = Parameters.build {
                append("fid",fid.toString())
                append("act",RelationAction.SUBSCRIBE.value.toString())
                append("re_src",re_src.value.toString())
                append("csrf",bot.cookies.bili_jct)
            }
        ) {
            withUserCookies(bot.cookies)
        }
    }

    override fun relationModifyAsync(
        fid: Long,
        action: RelationAction,
        re_src: RelationSubscribeSrc,
        access_key: String?
    ): Deferred<RelationModifyResponse> = async {
         client.submitForm(
             url = BiliApiUrl.relationModify(),
             formParameters = Parameters.build {
                 append("fid",fid.toString())
                 append("act",action.value.toString())
                 append("re_src",re_src.value.toString())
                 append("csrf",bot.cookies.bili_jct)
             }
         ) {
             withUserCookies(bot.cookies)
         }
    }

    override fun relationBatchModifyAsync(
        vararg fids: Long,
        action: RelationAction,
        re_src: RelationSubscribeSrc,
        access_key: String?
    ): Deferred<RelationBatchModifyResponse> = async {
        client.submitForm(
            url = BiliApiUrl.relationBatchModify(),
            formParameters = Parameters.build {
                append("fids",fids.joinToString(","))
                append("act",action.value.toString())
                append("re_src",re_src.value.toString())
                append("csrf",bot.cookies.bili_jct)
            }
        ) {
            withUserCookies(bot.cookies)
        }
    }

    override fun searchAllAsync(keyword: String): Deferred<SearchAllWebResponse> = async {
        getWithCookies(BiliApiUrl.searchAllWeb(keyword))
    }

}