package com.elouyi.bely.event.internal

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel


internal class ElyReceiveChannel<out T>(
    private val delegate: ReceiveChannel<T>
) : ReceiveChannel<T> by delegate

internal class ElySendChannel<in T>(
    private val delegate: SendChannel<T>
) : SendChannel<T> by delegate
