package com.elouyi.bely.data.model.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class LiveDanmuExtra(

    @SerialName("send_from_me")
    val sendFromMe: Boolean,
    val content: String,
    val mode: Int,
    @SerialName("font_size")
    val fontSize: Int,
    val color: Int,
    val direction: Int,
    val pk_direction: Int,
    val user_hash: Long
)
