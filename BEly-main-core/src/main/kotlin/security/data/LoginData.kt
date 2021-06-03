package com.elouyi.bely.security.data

import kotlinx.serialization.Serializable

@Serializable
internal data class WebLoginParam(
    val captchaType: Int = 6,
    val username: String,
    val password: String,
    val keep: Boolean = true,
    val key: String,
    val challenge: String,
    val validate: String,
    val seccode: String
)

@Serializable
internal data class QRCodeData(
    val url: String,
    val oauthKey: String
)

@Serializable
internal data class QRCodeResponse(
    val code: Int,
    val status: Boolean,
    val ts: Long,
    val data: QRCodeData
)

@Serializable
internal data class QRCodeLoginInfo(
    val code: Int,
    val message: String?,
    val ts: Long?,
    val status: Boolean,
    val data: QRCodeLoginData
)

@Serializable
internal data class QRCodeLoginData(
    val url: String
)