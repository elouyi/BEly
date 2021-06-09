package com.elouyi.bely.utils

import java.security.MessageDigest

private val utilLogger = ElyLogger("StringUtils")

/**
 * 计算一个 [ByteArray] 的 md5 值，注意，返回类型是 [ByteArray]
 * @return md5 的值
 */
fun ByteArray.md5(offset: Int = 0,length: Int = size - offset): ByteArray = when {
    offset < 0 -> {
        utilLogger.e("md5: param offset is less than 0")
        "".toByteArray()
    }
    length < 0 -> {
        utilLogger.e("md5: param length is less than 0")
        "".toByteArray()
    }
    offset + length > size -> {
        utilLogger.e("md5: offset plus length is more than size")
        "".toByteArray()
    }
    else -> {
        MessageDigest.getInstance("MD5").run {
            update(this@md5,offset,length)
            digest()
        }
    }
}

/**
 * 计算一个字符串的32位小写md5，返回值为 [String]
 * @return md5 的计算结果
 */
fun String.md5(): String = buildString {
    val md5Bytes = toByteArray().md5()
    for (b in md5Bytes) {
        var hex = Integer.toHexString(b.toInt() and 0xff)
        if (hex.length < 2) {
            hex = "0$hex"
        }
        append(hex)
    }
}