package com.elouyi.bely.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

fun now(): String{
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.now().format(format)
}

@OptIn(ExperimentalContracts::class)
inline fun measureCost(block: () -> Unit): Long {
    contract {
        callsInPlace(block,InvocationKind.EXACTLY_ONCE)
    }
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}

@OptIn(ExperimentalContracts::class)
inline fun <T> measureCostReturns(block: () -> T): Pair<Long,T> {
    contract {
        callsInPlace(block,InvocationKind.EXACTLY_ONCE)
    }
    val start = System.currentTimeMillis()
    val r = block()
    return System.currentTimeMillis() - start to r
}