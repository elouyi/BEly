package com.elouyi.bely.utils.internal

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext


internal object GlobalCEH : CoroutineExceptionHandler, CoroutineContext.Key<CoroutineExceptionHandler> {
    override val key: CoroutineContext.Key<*> get() = GlobalCEH

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        println("协程异常")
        println(context)
        exception.printStackTrace()
    }

}