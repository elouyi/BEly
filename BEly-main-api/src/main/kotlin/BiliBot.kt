package com.elouyi.bely

import com.elouyi.bely.utils.ElyLogger

interface BiliBot {

    val uid: Long

    val name: String

    val logger: ElyLogger

    suspend fun login()
}