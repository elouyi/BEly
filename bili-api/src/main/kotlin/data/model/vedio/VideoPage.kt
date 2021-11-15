package com.elouyi.bely.data.model.vedio

import kotlinx.serialization.Serializable


@Serializable
public data class VideoPage(

    /**
     * 当前分P cid
     */
    val cid: Int,

    /**
     * 当前分P
     */
    val page: Int,

    /**
     * 视频来源
     * vupload：普通上传（B站）
     * hunan：芒果TV
     * qq：腾讯
     */
    val from: String,

    /**
     * 当前分P标题
     */
    val part: String,

    /**
     * 当前分P持续时间,单位为秒
     */
    val duration: Int,

    /**
     * 站外视频vid
     */
    val vid: String?,

    /**
     * 	站外视频跳转url
     */
    val weblink: String?,

    /**
     * 当前分P分辨率
     */
    val dimension: VideoDimension
)
