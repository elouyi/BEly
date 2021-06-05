package com.elouyi.bely.biliapi

import com.elouyi.bely.biliapi.data.personal.AccountInfoResponse
import com.elouyi.bely.biliapi.data.personal.ExpResponse
import com.elouyi.bely.biliapi.data.personal.RewardResponse
import com.elouyi.bely.biliapi.data.personal.VipInfoResponse
import com.elouyi.bely.contact.WebBiliBotImpl
import com.elouyi.bely.security.utils.withUserCookies
import io.ktor.client.request.*
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
}