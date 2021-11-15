package com.elouyi.bely.data.model.vedio

import kotlinx.serialization.Serializable


@Serializable
public data class VideoHonorReply(
    val honor: List<Honor>?
) {

    @Serializable
    public data class Honor(
        val aid: Int,
        val type: Int,
        val desc: String,
        val weekly_recommend_num: Int
    )
}
