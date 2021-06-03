package com.elouyi.bely

import com.elouyi.bely.publicapi.PublicApi
import com.elouyi.bely.utils.newInstance
import io.ktor.client.*
import java.util.*
import kotlin.reflect.KClass

val BEly: IBEly get() = _BEly.getInstance()

interface IBEly {

    /**
     * 带浏览器 agent和 json 序列化的 [HttpClient]
     */
    val browserClient: HttpClient

    /**
     * 无需登录的 API
     */
    val publicApi: PublicApi
}

@Suppress("ClassName","UncheckedCast")
private object _BEly {

    @JvmField
    var instance: IBEly? = null

    fun getInstance(): IBEly {

        return instance ?: run {
            val cl = Class.forName("com.elouyi.bely.BElyImpl").kotlin as KClass<IBEly>
            try {
                cl.newInstance()
            } catch (e: Exception) {
                throw e
            }.also { instance = it }
        }

    }
}