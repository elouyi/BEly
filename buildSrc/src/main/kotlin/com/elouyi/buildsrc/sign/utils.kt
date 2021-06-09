package com.elouyi.buildsrc.sign

import java.io.File
import java.util.*

fun decryptBase64(base64: String): String {
    println("generate ring file")
    val fileName = "C:/secring.gpg"
    val file = File(fileName)
    file.createNewFile()
    val b = Base64.getDecoder().decode(base64)
    file.outputStream().use {
        it.write(b)
    }
    return file.absolutePath
}