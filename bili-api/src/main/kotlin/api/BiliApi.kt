package com.elouyi.bely.api

import com.elouyi.bely.api.internal.LiveApiImpl
import com.elouyi.bely.api.internal.VideoApiImpl
import com.elouyi.bely.data.internal.repository.BElyRepository


public interface BiliApi {

    public companion object {

        public val videoApi: VideoApi get() = VideoApiImpl

        public val liveApi: LiveApi by lazy {
            LiveApiImpl(BElyRepository.live)
        }
    }
}
