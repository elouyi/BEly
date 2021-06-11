package com.elouyi.bely

import com.elouyi.bely.config.BotConfigurationBuilder
import com.elouyi.bely.contact.BiliBot
import com.elouyi.bely.contact.WebBiliBot
import com.elouyi.bely.contact.WebBiliBotImpl
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

    operator fun getValue(p1: Any,p2: KProperty<*>): BiliBotFactory = this
}


