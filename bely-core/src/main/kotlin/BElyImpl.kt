package com.elouyi.bely

import com.elouyi.bely.config.BotConfigurationBuilder
import com.elouyi.bely.contact.BiliBot
import com.elouyi.bely.contact.WebBiliBot
import com.elouyi.bely.publicapi.PublicApiImpl
import com.elouyi.bely.publicapi.PublicApi
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*

internal open class BElyImpl : IBEly {

    override val browserClient: HttpClient = HttpClient(CIO) {
        BrowserUserAgent()
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(HttpTimeout) {
            connectTimeoutMillis = 5000
            requestTimeoutMillis = 5000
            socketTimeoutMillis = 5000
        }
    }

    override val publicApi: PublicApi = PublicApiImpl

    override val botFactory: BiliBotFactory = object : BiliBotFactory {
        override fun newWebBot(uid: Long, config: BotConfigurationBuilder.() -> Unit): WebBiliBot =
            com.elouyi.bely.newWebBot(uid, config)

        override fun newAppBot(uid: Long, config: BotConfigurationBuilder.() -> Unit): BiliBot =
            com.elouyi.bely.newAppBot(uid, config)
    }
}