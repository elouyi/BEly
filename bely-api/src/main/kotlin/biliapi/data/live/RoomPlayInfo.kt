package com.elouyi.bely.biliapi.data.live

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Serializable

@Serializable
data class RoomPlayInfoResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: RoomPlayInfoData
) : BiliResponse<RoomPlayInfoData>

@Serializable
data class RoomPlayInfoData(
    val room_id: Long,
    val short_id: Int,
    val need_p2p: Int,
    val is_hidden: Boolean,
    val is_locked: Boolean,
    val is_portrait: Boolean,
    val live_statue: Int,
    val hidden_till: Int,
    val lock_till: Int,
    val encrypted: Boolean,
    val pwd_verified: Boolean,
    val live_time: Long,
    val room_shield: Int,
    val is_sp: Int,
    val special_type: Int,
    val play_url: String?,
    val all_special_types: List<Int>
)