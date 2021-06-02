package com.elouyi.bely.publicapi.response

import kotlinx.serialization.Serializable

@Serializable
data class RelationResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: RelationData,
) : BiliResponse<RelationData>