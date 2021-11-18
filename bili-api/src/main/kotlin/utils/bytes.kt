package com.elouyi.bely.utils

import com.elouyi.bely.data.internal.BiliHead
import io.ktor.utils.io.core.*


internal fun ByteReadPacket.readBiliHead(): BiliHead = BiliHead(
    readInt().toLong(),
    readShort().toInt(),
    readShort().toInt(),
    readInt(),
    readInt()
)

