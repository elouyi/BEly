package com.elouyi.bely.data.response.video

import com.elouyi.bely.data.BiliResponse
import com.elouyi.bely.data.model.vedio.VideoData
import kotlinx.serialization.Serializable


@Serializable
public data class VideoDataResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: VideoData?
) : BiliResponse<VideoData?>
