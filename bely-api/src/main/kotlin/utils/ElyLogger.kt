package com.elouyi.bely.utils

import com.elouyi.bely.BEly

interface ElyLogger {

    val owner: String

    var level: LogLevel

    var out: (Any?) -> Unit

    fun v(message: Any?)

    fun d(message: Any?)

    fun i(message: Any?)

    fun w(message: Any?)

    fun e(message: Any?): Unit = e(message,null)

    fun e(e: Throwable): Unit = e(null,e)

    fun e(message: Any?,e: Throwable?)

    fun setLevel(l: Int)

    companion object {
        operator fun invoke(owner: String): ElyLogger = BEly.loggerFactory.newLogger(owner)
    }
}


/**
 * 日志级别
 */
sealed class LogLevel(val lv: Int) : Comparable<LogLevel>{

    object VERBOSE : LogLevel(1)
    object DEBUG : LogLevel(2)
    object INFO : LogLevel(3)
    object WARN : LogLevel(4)
    object ERROR : LogLevel(5)
    object DISABLE : LogLevel(100)

    companion object{
        fun getLevel(l: Int) = when{
            l < 2 -> VERBOSE
            l < 3 -> DEBUG
            l < 4 -> INFO
            l < 5 -> WARN
            l < 6 -> ERROR
            else -> DISABLE
        }
    }

    override operator fun compareTo(other: LogLevel) = this.lv.compareTo(other.lv)

    override fun equals(other: Any?) = when(other){
        is LogLevel -> this.lv == other.lv
        is Int -> this.lv == other
        else -> false  //super.equals(other)
    }

    override fun hashCode() = lv
}

abstract class ElyLoggerAbstract(override val owner: String) : ElyLogger {

    override var level: LogLevel = LogLevel.VERBOSE

    override var out: (Any?) -> Unit = {
        println(it.toString())
    }

    override fun setLevel(l: Int){
        level = LogLevel.getLevel(l)
    }

    final override fun v(message: Any?) {
        if (level > LogLevel.VERBOSE) return
        vv(message.toString())
    }

    final override fun d(message: Any?) {
        if (level > LogLevel.DEBUG) return
        dd(message.toString())
    }

    final override fun i(message: Any?) {
        if (level > LogLevel.INFO) return
        ii(message.toString())
    }

    final override fun w(message: Any?) {
        if (level > LogLevel.WARN) return
        ww(message.toString())
    }

    final override fun e(message: Any?,e: Throwable?) {
        if (level > LogLevel.ERROR) return
        ee(message.toString(),e)
    }


    protected abstract fun vv(message: String)
    protected abstract fun dd(message: String)
    protected abstract fun ii(message: String)
    protected abstract fun ww(message: String)
    protected abstract fun ee(message: String,e: Throwable?)
}