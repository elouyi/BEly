package com.elouyi.buildsrc.sign

import java.io.File
import java.util.*

fun decryptBase64(base64: String): String {
    val fileName = "secring.gpg"
    val file = File(fileName).also {
        it.createNewFile()
    }
    val b = Base64.getDecoder().decode(base64)
    file.outputStream().use {
        it.write(b)
    }
    return file.absolutePath
}