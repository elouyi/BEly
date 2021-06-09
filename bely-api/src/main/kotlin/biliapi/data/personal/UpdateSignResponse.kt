package com.elouyi.bely.biliapi.data.personal

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class UpdateSignResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    @Contextual
    override val data: Nothing? = null
) : BiliResponse<Nothing?>
