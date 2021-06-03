package com.elouyi.bely

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
}