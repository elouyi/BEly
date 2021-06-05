package com.elouyi.bely.contact

import com.elouyi.bely.biliapi.BiliApi
import com.elouyi.bely.config.BotConfiguration
import com.elouyi.bely.security.Verification
import com.elouyi.bely.security.data.QRCodeLoginInfo
import com.elouyi.bely.security.utils.*
import com.elouyi.bely.security.utils.DedeUserID
import com.elouyi.bely.security.utils.DedeUserID__ckMd5
import com.elouyi.bely.security.utils.SESSDATA
import com.elouyi.bely.security.utils.UserCookies
import com.elouyi.bely.security.utils.bili_jct
import com.elouyi.bely.utils.ElyLogger
import com.elouyi.bely.utils.QRUtil
import com.elouyi.bely.utils.readLinec
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal abstract class AbstractBiliBot(
    auid: Long,
    var config: BotConfiguration
) : BiliBot {

    final override var uid: Long = auid
        @Deprecated("应该不需要改动了",level = DeprecationLevel.ERROR)
        protected set(value) {
            logger.e("Bot uid 变动: $field -> $value")
            field = value
            logger = ElyLogger(value.toString())
        }

    override var logger: ElyLogger = ElyLogger(uid.toString())

    override val client: HttpClient = HttpClient(OkHttp) {
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

    protected open suspend fun loginWithQRCode(): IUserCookies? {
        logger.e("${javaClass.simpleName} 不支持验证码登录")
        return null
    }

}