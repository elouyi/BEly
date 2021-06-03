package com.elouyi.bely.security.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property hash 密码校验盐值，有效时间 20s
 * @property key RSA 公玥
 */
@Serializable
internal data class KeyResponse(
    val hash: String,
    val key: String
) {
    val keyStr: String get() {
        return key
            .substringAfter("KEY-----\n")
            .substringBeforeLast("\n-----END")
            .replace("\n","")
    }
}

@Serializable
internal data class KeyParam(
    //@SerialName("")
    val appkey: String,
    val sign: String
)
