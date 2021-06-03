@file:Suppress("ClassName")

package com.elouyi.bely.security.utils

import io.ktor.http.*

operator fun Headers.get(sessdata: SESSDATA): SESSDATA {
    val str = toString()
    if (!str.contains("SESSDATA=")) throw Exception("不存在 SESSDATA")
    return SESSDATA(
        str.substringAfter("SESSDATA=")
            .substringBefore(";")
    )
}

open class SESSDATA(val value: String) {
    companion object : SESSDATA("")
}