package com.elouyi.bely.utils

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

/**
 * readline 专用协程调度器
 */
private val readLineDedicatedThreadDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

internal suspend fun readLinec(): String? =
    withContext(readLineDedicatedThreadDispatcher) {
        readLine()
    }