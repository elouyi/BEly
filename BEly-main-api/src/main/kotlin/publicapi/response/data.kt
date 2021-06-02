package com.elouyi.bely.publicapi.response

import kotlinx.serialization.Serializable

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