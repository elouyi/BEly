package com.elouyi.bely.publicapi.response

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Serializable

@Serializable
data class MasterPieceResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: List<MasterPieceData>,
) : BiliResponse<List<MasterPieceData>>