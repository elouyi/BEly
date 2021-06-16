package com.elouyi.bely

import com.elouyi.bely.config.BotConfigurationBuilder
import com.elouyi.bely.contact.BiliBot
import com.elouyi.bely.contact.WebBiliBot
import com.elouyi.bely.contact.WebBiliBotImpl
import com.elouyi.bely.security.utils.UserCookieCache
import com.elouyi.bely.security.utils.UserCookies
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KProperty

internal object BiliBotFactoryImpl : BiliBotFactory {
    override fun newAppBot(uid: Long, config: BotConfigurationBuilder.() -> Unit): BiliBot {
        TODO("Not yet implemented")
    }


    override fun newWebBot(uid: Long, config: BotConfigurationBuilder.() -> Unit): WebBiliBot {
        val configurationBuilder = BotConfigurationBuilder()
        configurationBuilder.config()
        return WebBiliBotImpl(uid,configurationBuilder.build())
    }

    override fun newWebBotWithCookies(
        SESSDATA: String,
        DedeUserID: Long,
        DedeUserID__ckMd5: String,
        bili_jct: String
    ): WebBiliBot {
        val cookies = UserCookies.newCookies(SESSDATA,DedeUserID,DedeUserID__ckMd5,bili_jct)
        runBlocking {
            UserCookieCache.saveCookies(cookies)
        }
        return WebBiliBotImpl(DedeUserID,BotConfigurationBuilder().build())
    }

    operator fun getValue(p1: Any,p2: KProperty<*>): BiliBotFactory = this
}


