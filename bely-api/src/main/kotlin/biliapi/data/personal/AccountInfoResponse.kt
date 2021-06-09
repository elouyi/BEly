package com.elouyi.bely.biliapi.data.personal

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Serializable

/**
 * 我的信息
 * @see AccountInfoData
 */
@Serializable
data class AccountInfoResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: AccountInfoData
) : BiliResponse<AccountInfoData>