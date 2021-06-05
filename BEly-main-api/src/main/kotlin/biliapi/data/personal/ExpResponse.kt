package com.elouyi.bely.biliapi.data.personal

import com.elouyi.bely.publicapi.response.BiliResponse
import kotlinx.serialization.Serializable

/**
 * 每日经验
 */
@Serializable
data class ExpResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: Int
) : BiliResponse<Int>