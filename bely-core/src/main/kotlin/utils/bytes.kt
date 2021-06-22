package com.elouyi.bely.utils

import java.io.ByteArrayOutputStream
import java.util.zip.Inflater

fun decompress(bytes: ByteArray): ByteArray {
    val output: ByteArray
    val decompresser = Inflater()
    decompresser.reset()
    decompresser.setInput(bytes)
    val o = ByteArrayOutputStream(bytes.size)
    val buf = ByteArray(1024)
    while (!decompresser.finished()) {
        val i = decompresser.inflate(buf)
        o.write(buf,0,i)
    }
    output = o.toByteArray()
    o.close()
    return output
}