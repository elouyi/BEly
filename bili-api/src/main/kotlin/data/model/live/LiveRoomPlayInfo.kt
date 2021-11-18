package data.model.live

import kotlinx.serialization.Serializable

@Serializable
public data class LiveRoomPlayInfo(
    val room_id: Int,
    val short_id: Int,
    val need_p2p: Int,
    val is_hidden: Boolean,
    val is_locked: Boolean,
    val is_portrait: Boolean,
    val live_status: Int,
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
