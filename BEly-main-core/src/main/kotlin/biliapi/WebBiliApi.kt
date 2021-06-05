package com.elouyi.bely.biliapi

import com.elouyi.bely.biliapi.data.personal.AccountInfoResponse
import com.elouyi.bely.biliapi.data.personal.RewardResponse
import com.elouyi.bely.contact.WebBiliBot
import com.elouyi.bely.security.utils.withUserCookies
import io.ktor.client.request.*
import kotlinx.coroutines.*

internal class WebBiliApi(
    override val bot: WebBiliBot
) : BiliApi {

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
}