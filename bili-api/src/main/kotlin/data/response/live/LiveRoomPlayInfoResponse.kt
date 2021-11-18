package com.elouyi.bely.data.response.live

import com.elouyi.bely.data.BiliResponse
import data.model.live.LiveRoomPlayInfo
import kotlinx.serialization.Serializable


@Serializable
public data class LiveRoomPlayInfoResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: LiveRoomPlayInfo?
) : BiliResponse<LiveRoomPlayInfo?>
