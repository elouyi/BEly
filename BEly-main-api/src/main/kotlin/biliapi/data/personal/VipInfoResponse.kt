package com.elouyi.bely.biliapi.data.personal

import com.elouyi.bely.publicapi.response.BiliResponse
import kotlinx.serialization.Serializable

@Serializable
data class VipInfoResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: VipInfoData
) : BiliResponse<VipInfoData>