package com.elouyi.bely.biliapi.data.live

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Serializable

@Serializable
data class LiveDanmuInfoResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: LiveDanmuInfoData
) : BiliResponse<LiveDanmuInfoData>

@Serializable
data class LiveDanmuInfoData(
    val group: String,
    val business_id: Int,
    val refresh_row_factor: Double,
    val refresh_rate: Int,
    val max_delay: Int,
    val token: String,
    val host_list: List<LiveDanmuInfoHostList>
)

@Serializable
data class LiveDanmuInfoHostList(
    val host: String,
    val port: Int,
    val wss_port: Int,
    val ws_port: Int
)