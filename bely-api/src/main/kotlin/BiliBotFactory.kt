package com.elouyi.bely

import com.elouyi.bely.config.BotConfigurationBuilder
import com.elouyi.bely.contact.BiliBot
import com.elouyi.bely.contact.WebBiliBot

/**
 * 构造 [BiliBot] 的方式
 *
 * examples:
 * ```kotlin
 * val bot = BiliBotFactory.newBot(uid){  // this: BotConfigurationBuilder
 *  ...
 * }
 * ```
 */
interface BiliBotFactory {

    /**
     * 构建一个 web 协议的 bili bot
     * @param uid 用户uid
     * @param config 配置
     * @see BotConfigurationBuilder
     */
    fun newWebBot(uid: Long, config: BotConfigurationBuilder.() -> Unit = {}): WebBiliBot

    // todo
    fun newAppBot(uid: Long, config: BotConfigurationBuilder.() -> Unit = {}): BiliBot

    /**
     * 通过 cookie 直接构建一个 [WebBiliBot]
     */
    fun newWebBotWithCookies(
        SESSDATA: String,
        DedeUserID: Long,
        DedeUserID__ckMd5: String,
        bili_jct: String
    ): WebBiliBot

    companion object : BiliBotFactory by BEly.botFactory
}