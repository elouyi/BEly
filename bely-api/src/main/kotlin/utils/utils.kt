package com.elouyi.bely.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun now(): String{
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.now().format(format)
}