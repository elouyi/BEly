package com.elouyi.bely.utils

import com.elouyi.bely.biliapi.WebBiliApi
import com.elouyi.bely.contact.WebBiliBotImpl
import com.elouyi.bely.security.utils.withUserCookies
import io.ktor.client.request.*

@Suppress("LocalVariableName")
internal suspend inline fun <reified T> WebBiliApi.getWithCookies(url: String): T {
    return client.get(url) {
        val _bot = bot as WebBiliBotImpl
        withUserCookies(_bot.cookies)
    }
}