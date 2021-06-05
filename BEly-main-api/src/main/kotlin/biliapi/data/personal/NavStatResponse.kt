package com.elouyi.bely.biliapi.data.personal

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Serializable

@Serializable
data class NavStatResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: NavStatData
) : BiliResponse<NavStatData>
