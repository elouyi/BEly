@file:Suppress("Unused")

package com.elouyi.bely.utils.annotation

@RequiresOptIn("这个 api 是不稳定的，可能会出现意料之外的情况",RequiresOptIn.Level.WARNING)
annotation class UnstableApi

@RequiresOptIn("这个 api 不再支持，通常会抛出一个异常，并且在未来会被移除",RequiresOptIn.Level.ERROR)
annotation class UnSupportedApi