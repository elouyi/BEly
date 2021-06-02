package com.elouyi.buildsrc

import Versions

/**
 * 声明 kotlinx 库的工具函数
 */
fun kotlinx(id: String,version: String): String = "org.jetbrains.kotlinx:kotlinx-$id:$version"

/**
 * 声明 ktor 库的工具函数
 */
fun ktor(id: String,version: String = Versions.ktor): String = "io.ktor:ktor-$id:$version"

/**
 * 声明 ktorClient 库的工具函数
 */
fun ktorClient(id: String,version: String = Versions.ktor): String = ktor("client-$id",version)

/**
 * 声明 ktorServer 库的工具函数
 */
fun ktorServer(id: String,version: String = Versions.ktor): String = ktor("server-$id",version)

val ktorClientCore: String get() = ktorClient("core")

val coroutines: String get() = kotlinx("coroutines-core",Versions.coroutines)