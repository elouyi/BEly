package com.elouyi.bely.data.model.vedio

import com.elouyi.bely.data.model.contact.VideoOwner
import com.elouyi.bely.data.model.contact.VideoStaff
import com.elouyi.bely.data.model.sub.UserGarb
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable


@Serializable
public data class VideoData(

    /**
     * 稿件 bvid
     */
    val bvid: String,

    /**
     * 稿件 aid
     */
    val aid: Int,

    /**
     * 稿件分 p 总数 默认为1
     */
    val videos: Int,

    /**
     * 分区 tid
     */
    val tid: Int,

    /**
     * 子分区名称
     */
    val tname: String,

    /**
     * 视频类型 1：原创，2：转载
     */
    val copyright: Int,

    /**
     * 稿件封面图片 url
     */
    val pic: String,

    /**
     * 稿件标题
     */
    val title: String,

    /**
     * 稿件发布时间戳
     */
    val pubdate: Long,

    /**
     * 投稿时间戳
     */
    val ctime: Long,

    /**
     * 视频简介
     */
    val desc: String,

    /**
     * 新版视频简介
     */
    val desc_v2: List<VideoDescV2>,

    /**
     * 视频状态
     */
    val state: Int,

    /**
     * 稿件总时长，单位秒
     */
    val duration: Int,

    /**
     * 撞车视频跳转 avid
     */
    val forward: Int?,

    /**
     * 稿件参与的活动 id
     */
    val mission_id: Int?,

    /**
     * 充电线 url，仅存在于番剧和影视视频
     */
    val redirect_url: String?,

    /**
     * 视频属性标志
     */
    val rights: VideoRights,

    /**
     * 视频 up 主信息
     */
    val owner: VideoOwner,

    /**
     * 视频状态数
     */
    val stat: VideoStat,

    /**
     * 视频同步发布的动态文字内容
     */
    val dynamic: String,

    /**
     * 视频 1P cid
     */
    val cid: Int,

    /**
     * 视频 1P 分辨率
     */
    val dimension: VideoDimension,

    /**
     * true
     */
    val no_cache: Boolean,

    /**
     * 视频分 P 列表
     */
    val pages: List<VideoPage>,

    /**
     * 视频CC字幕信息
     */
    val subtitle: VideoSubtitle,

    /**
     * 合作成员列表
     */
    val staff: VideoStaff?,

    /**
     * 用户装扮信息
     */
    val user_garb: UserGarb,

    val honor_reply: VideoHonorReply
)
