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
    val is_cooperation: Int
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