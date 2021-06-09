package com.elouyi.bely.publicapi.response

import kotlinx.serialization.Serializable

/// BiliResponse<out T> 中的 T ,格式为 xxxData

@Serializable
data class VideoData (
    val bvid: String,
    val aid: Int,
    val videos: Int,
    val tid: Int,
    val tname: String,
    val copyright: Int,
    val pic: String,
    val title: String,
    val pubdate: Long,
    val ctime: Long,
    val desc: String,
    val duration: Int,
    val rights: VideoInfo,
    val owner: Up,
    val stat: VideoState,
    val dynamic: String,
)

/**
 * 用户关系
 * @property mid UID
 * @property following 关注数
 * @property whisper 悄悄关注数
 * @property black 黑名单数
 * @property follower 粉丝数
 */
@Serializable
data class RelationData(
    val mid: Long,
    val following: Int,
    val whisper: Int,
    val black: Int,
    val follower: Int,
)

/**
 * up主代表作,一般是一个 [List]
 */
typealias MasterPieceData = VideoData

/*
@Serializable
data class MasterPieceData(
    val aid: Long,
    val videos: Int,
    val tname: String,
    val copyright: Int,
    val pic: String
)*/


@Serializable
data class AccInfoData(
    val mid: Long,
    val name: String,
    val sex: String,
    val face: String,
    val sign: String,
    val rank: Long,
    val level: Int,
    val jointime: Long,
    val mornal: Long,
    val silence: Int,
    val birthday: String,
    val coins: Int,
    val fan_badge: Boolean,
    val official: UserVerify,
    val vip: Vip,
    val live_room: LiveRoom
)



/// data 中的其他对象


/**
 * up主
 */
@Serializable
data class Up(
    val mid: Long,
    val name: String,
    val face: String
)

@Serializable
data class VideoInfo(
    val bp: Int,
    val elec: Int,
    val download: Int,
    val movie: Int,
    val pay: Int,
    val hd5: Int,
    val no_reprint: Int,
    val autoplay: Int,
    val is_cooperation: Int,
    val nameplate: NamePlate,
    val is_followed: Boolean,
    val top_photo: String
)

@Serializable
data class VideoState(
    val view: Int,
    val danmaku: Int,
    val reply: Int,
    val favorite: Int,
    val coin: Int,
    val now_rank: Int,
    val his_rank: Int,
    val like: Int
)

/**
 * 认证信息
 */
@Serializable
data class UserVerify(
    val type: Int,
    val desc: String,
    val role: Int,
    val title: String
)

/**
 * 大会员 信息
 * @property type 0 为非会员，1 为大会员，2 年度大会员
 */
@Serializable
data class Vip(
    val type: Int
)

/**
 * 勋章信息?
 */
@Serializable
data class NamePlate(
    val nid: Int,
    val name: String,
    val image: String,
    val image_small: String,
    val level: String,
    val condition: String
)

/**
 * 直播间信息
 */
@Serializable
data class LiveRoom(
    val roomStatus: Int,
    val liveStatus: Int,
    val url: String,
    val title: String,
    val cover: String,
    val online: Int,
    val roomid: Long,
    val roundStatue: Int,
    val broadcast_type: Int
)