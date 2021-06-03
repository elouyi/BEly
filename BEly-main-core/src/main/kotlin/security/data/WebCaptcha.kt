package com.elouyi.bely.security.data

import kotlinx.serialization.Serializable

@Serializable
internal data class WebCaptchaResponse(
    val code: Int,
    val data: WebCaptchaData
)

@Serializable
internal data class WebCaptchaData(
    val type: Int,
    val result: WebCaptchaResult
)

/**
 * @property gt 极验 id
 * @property challenge 极验 key
 * @property key 登录密钥
 */
@Serializable
internal data class WebCaptchaResult(
    val success: Int,
    val gt: String,
    val challenge: String,
    val key: String
)