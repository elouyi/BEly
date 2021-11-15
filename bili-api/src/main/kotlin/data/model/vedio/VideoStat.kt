package com.elouyi.bely.data.model.vedio

import kotlinx.serialization.Serializable


@Serializable
public data class VideoStat(

    /**
     * 稿件avid
     */
    val aid: Int,

    /**
     * 稿件avid
     */
    val view: Int,

    /**
     * 弹幕数
     */
    val danmaku: Int,

    /**
     * 评论数
     */
    val reply: Int,

    /**
     * 收藏数
     */
    val favorite: Int,

    /**
     * 投币数
     */
    val coin: Int,

    /**
     * 分享数
     */
    val share: Int,

    /**
     * 当前排名
     */
    val now_rank: Int,

    /**
     * 历史最高排行
     */
    val his_rank: Int,

    /**
     * 获赞数
     */
    val like: Int,

    /**
     * 点踩数 0
     */
    val dislike: Int,

    /**
     * 视频评分
     */
    val evaluation: String,

    /**
     * 警告/争议提示信息
     */
    val argue_msg: String
)
