package com.elouyi.buildsrc.sign

import java.io.File
import java.io.FileOutputStream
import java.util.*

fun decryptBase64(base64: String): String {
    println("generate ring file")
    val fileName = "secring.gpg"
    val file = File(fileName)
    file.createNewFile()
    val b = Base64.getDecoder().decode(base64)
    FileOutputStream(file).use {
        it.write(b)
    }
    println(file.exists())
    return file.absolutePath
}