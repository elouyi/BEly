package com.elouyi.bely.utils

enum class ElyColor(val color: String){
    ANSI_RESET("\u001B[0m"),
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_WHITE("\u001B[37m");

    override fun toString() = color
}