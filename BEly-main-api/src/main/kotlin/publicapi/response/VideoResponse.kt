package com.elouyi.bely.publicapi.response

import kotlinx.serialization.Serializable

@Serializable
data class VideoResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: VideoData
) : BiliResponse<VideoData>