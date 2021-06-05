package com.elouyi.bely

import com.elouyi.bely.config.BotConfigurationBuilder
import com.elouyi.bely.contact.BiliBot
import com.elouyi.bely.contact.WebBiliBot


fun BiliBotFactory.Companion.newAppBot(uid: Long, config: BotConfigurationBuilder.() -> Unit = {}): BiliBot {
    TODO("Not yet implemented")
}

/**
 * 构建一个 web 协议的 bili bot
 * @param uid 用户uid
 * @param config 配置
 * @see BotConfigurationBuilder
 */
fun BiliBotFactory.Companion.newWebBot(uid: Long, config: BotConfigurationBuilder.() -> Unit = {}): BiliBot {
    val configurationBuilder = BotConfigurationBuilder()
    configurationBuilder.config()
    return WebBiliBot(uid,configurationBuilder.build())
}