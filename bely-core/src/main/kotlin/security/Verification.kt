package com.elouyi.bely.security

import com.elouyi.bely.security.data.KeyParam
import com.elouyi.bely.security.data.WebCaptchaResponse
import com.elouyi.bely.security.data.KeyResponse
import com.elouyi.bely.security.data.QRCodeResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*

internal object Verification {

    private const val webCaptchaUrl = "https://passport.bilibili.com/web/captcha/combine?plat=6"

    private const val webKeyUrl = "https://passport.bilibili.com/login?act=getkey"

    private const val appKeyUrl = "https://passport.bilibili.com/api/oauth2/getKey"

    private const val qrCodeLoginUrl = "https://passport.bilibili.com/qrcode/getLoginUrl"

    private const val qrcodeLoginInfo = "https://passport.bilibili.com/qrcode/getLoginInfo"

    private val client: HttpClient = HttpClient(CIO) {
        BrowserUserAgent()
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(HttpTimeout) {
            socketTimeoutMillis = 5000
            requestTimeoutMillis = 5000
            connectTimeoutMillis = 5000
        }
    }

    /**
     * 申请验证码参数
     */
    suspend fun webCaptcha(): WebCaptchaResponse {
        return client.get(webCaptchaUrl)
    }

    suspend fun webGetKey(): KeyResponse {
        return client.get(webKeyUrl)
    }

    suspend fun appGetKey(appKey: String,sign: String): KeyResponse {
        return appGetKey(KeyParam(appKey,sign))
    }

    suspend fun appGetKey(param: KeyParam): KeyResponse {
        return client.post(appKeyUrl) {
            body = param
        }
    }

    suspend fun getQRCodeLoginUrl(): QRCodeResponse =
        client.get(qrCodeLoginUrl)

    suspend fun getQRCodeLoginInfo(oauthKey: String): HttpResponse {
        return client.submitForm(
            url = qrcodeLoginInfo,
            formParameters = Parameters.build {
                append("oauthKey",oauthKey)
            },
            encodeInQuery = false
        )
    }
}