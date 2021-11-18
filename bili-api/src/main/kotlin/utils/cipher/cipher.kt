package com.elouyi.bely.utils.cipher

import java.io.ByteArrayOutputStream
import java.util.zip.Inflater


public fun decompress(bytes: ByteArray): ByteArray {
    val output: ByteArray
    val decompressor = Inflater()
    decompressor.reset()
    decompressor.setInput(bytes)
    val o = ByteArrayOutputStream(bytes.size)
    val buf = ByteArray(1024)
    try {
        while (!decompressor.finished()) {
            val i = decompressor.inflate(buf)
            o.write(buf,0,i)
        }
    } catch (e: Exception) {

    } finally {
        o.close()
    }

    output = o.toByteArray()
    return output
}