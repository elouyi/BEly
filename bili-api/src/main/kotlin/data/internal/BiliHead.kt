package com.elouyi.bely.data.internal


internal data class BiliHead(
    val totalLen: Long, // UInt
    val headLen: Int,
    val protover: Int,
    val type: Int,
    val s: Int
)
