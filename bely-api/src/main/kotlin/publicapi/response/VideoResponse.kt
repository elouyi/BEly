package com.elouyi.bely.publicapi.response

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Serializable

/**
 * 视频基本信息
 */
@Serializable
data class VideoResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: VideoData
) : BiliResponse<VideoData>