package com.elouyi.bely.biliapi

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
}