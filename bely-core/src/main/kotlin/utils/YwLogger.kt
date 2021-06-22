package com.elouyi.bely.utils

import com.elouyi.bely.utils.ElyColor
import com.elouyi.bely.utils.ElyLoggerAbstract
import com.elouyi.bely.utils.LogLevel
import com.elouyi.bely.utils.now

internal open class YwLogger(override val owner: String) : ElyLoggerAbstract(owner){

    override fun vv(message: String) {
        yout(message,"Verbose",ElyColor.ANSI_WHITE)
    }

    override fun dd(message: String) {
        yout(message,"Debug",ElyColor.ANSI_GREEN)
    }

    override fun ii(message: String) {
        yout(message,"Info",ElyColor.ANSI_BLUE)
    }

    override fun ww(message: String) {
        yout(message,"Warn",ElyColor.ANSI_YELLOW)
    }

    override fun ee(message: String, e: Throwable?) {
        if (message != "null") yout(message,"Error",ElyColor.ANSI_RED)
        e?.printStackTrace()
    }

    private val yout = { message: String?,level: String,color: ElyColor ->
        val content = "${now()} $level [$owner] : $message"
        out(color.toString() + content + ElyColor.ANSI_RESET)
    }

}