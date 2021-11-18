package com.elouyi.bely.data.response.live

import com.elouyi.bely.data.BiliResponse
import com.elouyi.bely.data.model.live.LiveDanmuInfo
import kotlinx.serialization.Serializable


@Serializable
public data class LiveDanmuInfoResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: LiveDanmuInfo?
) : BiliResponse<LiveDanmuInfo?>
