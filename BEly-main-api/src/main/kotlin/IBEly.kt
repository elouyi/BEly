package com.elouyi.bely

import io.ktor.client.*

val BEly: IBEly get() = _BEly.getInstance()

interface IBEly {

    /**
     * 带浏览器 agent和 json 序列化的 [HttpClient]
     */
    val browserClient: HttpClient
}

@Suppress("ClassName")
private object _BEly {

    @JvmField
    var instance: IBEly? = null

    fun getInstance(): IBEly {
        TODO()
    }
}