package com.elouyi.bely.data.model.live

import kotlinx.serialization.Serializable


@Serializable
public data class LiveDanmuInfo(
    val group: String,
    val business_id: Int,
    val refresh_row_factor: Double,
    val refresh_rate: Int,
    val max_delay: Int,
    val token: String,
    val host_list: List<Host>
) {

    @Serializable
    public data class Host(
        val host: String,
        val port: Int,
        val wss_port: Int,
        val ws_port: Int
    )
}
