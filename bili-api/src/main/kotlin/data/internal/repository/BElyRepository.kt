package com.elouyi.bely.data.internal.repository

import com.elouyi.bely.data.internal.repository.impl.LiveRepositoryImpl


internal object BElyRepository {

    val video: VideoRepository get() = TODO()

    val live: LiveRepository by lazy {
        LiveRepositoryImpl()
    }
}