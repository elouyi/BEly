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

    fun newWebBot(uid: Long, config: BotConfigurationBuilder.() -> Unit = {}): WebBiliBot

    fun newAppBot(uid: Long, config: BotConfigurationBuilder.() -> Unit = {}): BiliBot

    companion object : BiliBotFactory {
        override fun newWebBot(uid: Long, config: BotConfigurationBuilder.() -> Unit): WebBiliBot =
            BEly.botFactory.newWebBot(uid, config)

        override fun newAppBot(uid: Long, config: BotConfigurationBuilder.() -> Unit): BiliBot =
            BEly.botFactory.newAppBot(uid, config)

    }
}