package com.elouyi.bely.contact

import com.elouyi.bely.utils.ElyLogger
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*

internal abstract class AbstractBiliBot(auid: Long) : BiliBot {

    final override var uid: Long = auid
        set(value) {
            logger.e("Bot uid 变动: $field -> $value")
            field = value
            logger = ElyLogger(value.toString())
        }

    override var logger: ElyLogger = ElyLogger(uid.toString())

    override val client: HttpClient = HttpClient(CIO) {
        BrowserUserAgent()
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }
}