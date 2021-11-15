package com.elouyi.bely.secret.cookie

import io.ktor.client.request.*
import io.ktor.http.*

/**
 * 用于 header 取值
 * @see get
 */
public sealed interface CookieProperty {

    public object SESSDATA : CookieProperty
    public object DedeUserID : CookieProperty
    public object DedeUserID__ckMd5 : CookieProperty
    public object bili_jct : CookieProperty

}

private val CookieProperty.propertyName: String
    get() = javaClass.simpleName

/**
 * example:
 * ```kotlin
 * val sessdata = header[CookieProperty.SESSDATA]
 * ```
 */
public operator fun Headers.get(property: CookieProperty): String {
    val str = toString()
    if (!str.contains(property.propertyName)) throw Exception("header 中不存在 ${property.propertyName}")
    return str.substringAfter("${property.propertyName}=")
        .substringBefore(";")
}

public fun HttpRequestBuilder.withUserCookie(cookie: UserCookie) {
    cookie("SESSDATA",cookie.sessData)
    cookie("DedeUserID",cookie.dedeUserId)
    cookie("DedeUserID__ckMd5",cookie.dedeUserIDCkMd5)
    cookie("bili_jct",cookie.bili_jct)
}
