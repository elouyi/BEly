package com.elouyi.bely.internal

import com.elouyi.bely.IBEly
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
    }
}