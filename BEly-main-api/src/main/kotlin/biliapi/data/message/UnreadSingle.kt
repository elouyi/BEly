package com.elouyi.bely.biliapi.data.message

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Serializable

/**
 * 未读私信
 */
@Serializable
data class UnreadSingleResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: UnreadSingleData
) : BiliResponse<UnreadSingleData>

/**
 * 未读私信 data
 */
@Serializable
data class UnreadSingleData(
    val unfollow_unread: Int,
    val follow_unread: Int,
    val gt: Int
)