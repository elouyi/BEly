package com.elouyi.bely.utils

import com.elouyi.bely.utils.annotation.UnstableApi
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

interface LoggerFactory {
    fun newLogger(owner: String): ElyLogger

    @UnstableApi
    fun newLogger(owner: String,cl: KClass<out ElyLogger>): ElyLogger {
        return cl.primaryConstructor!!.call(owner)
    }
}