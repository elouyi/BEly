@file:Suppress("ClassName")

package com.elouyi.bely.security.utils

import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
internal data class UserCookies(
    val sessData: String,
    val dedeUserId: String,
    val dedeUserIDCkMd5: String,
    val bili_jct: String,
    private val expiresTime: Long = System.currentTimeMillis() + 29L * 24 * 60 * 60 * 1000
) {
    val isExpires: Boolean
        get() = System.currentTimeMillis() < expiresTime
}

/**
 * 用于 header 取值
 */
internal sealed interface CookieProperty


private val CookieProperty.propertyName: String
    get() = javaClass.simpleName


internal object SESSDATA : CookieProperty
internal object DedeUserID : CookieProperty
internal object DedeUserID__ckMd5 : CookieProperty
internal object bili_jct : CookieProperty


internal operator fun Headers.get(property: CookieProperty): String {
    val str = toString()
    if (!str.contains(property.propertyName)) throw Exception("不存在 ${property.propertyName}")
    return str.substringAfter("${property.propertyName}=")
        .substringBefore(";")
}


internal fun HttpRequestBuilder.withUserCookies(cookies: UserCookies) {
    cookie("SESSDATA",cookies.sessData)
    cookie("DedeUserID",cookies.dedeUserId)
    cookie("DedeUserID__ckMd5",cookies.dedeUserIDCkMd5)
    cookie("bili_jct",cookies.bili_jct)
}