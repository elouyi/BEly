package com.elouyi.bely.biliapi

import com.elouyi.bely.biliapi.data.message.*
import com.elouyi.bely.biliapi.data.personal.*
import com.elouyi.bely.contact.WebBiliBotImpl
import com.elouyi.bely.security.utils.UserCookieCache
import com.elouyi.bely.security.utils.withUserCookies
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.coroutines.*

internal class WebBiliApiImpl(
    override val bot: WebBiliBotImpl
) : WebBiliApi {

    override val coroutineContext = CoroutineName(
        "${bot.uid} biliApi" +
                SupervisorJob() +
                CoroutineExceptionHandler { _, throwable ->
                    bot.logger.e("PublicApi:",throwable)
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
}