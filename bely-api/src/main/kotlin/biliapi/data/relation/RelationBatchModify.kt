package com.elouyi.bely.biliapi.data.relation

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Serializable

/**
 * 批量操作用户关系回复
 */
@Serializable
data class RelationBatchModifyResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: RelationBatchModifyData
) : BiliResponse<RelationBatchModifyData>

/**
 * 操作失败的 uid
 */
@Serializable
data class RelationBatchModifyData(
    val failed_fids: List<Long>
)