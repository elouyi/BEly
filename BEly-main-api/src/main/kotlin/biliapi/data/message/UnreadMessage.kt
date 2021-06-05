package com.elouyi.bely.biliapi.data.message

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Serializable

@Serializable
data class UnreadMessageResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: UnreadMessageData
) : BiliResponse<UnreadMessageData>

@Serializable
data class UnreadMessageData(
    val at: Int,
    val chat: Int,
    val like: Int,
    val reply: Int,
    val sys_msg: Int,
    val up: Int,
)