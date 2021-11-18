package com.elouyi.bely.utils.internal

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.websocket.*


internal fun newClient(
    ignoreUnknownKeys: Boolean = true,
    explicitNulls: Boolean = false
): HttpClient = HttpClient(CIO) {
    val json = kotlinx.serialization.json.Json {
        this.ignoreUnknownKeys = ignoreUnknownKeys
        this.explicitNulls = explicitNulls
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }

    install(UserAgent) {
        agent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Ubuntu Chromium/70.0.3538.77 Chrome/70.0.3538.77 Safari/537.36"
    }

    install(WebSockets) {

    }
}
