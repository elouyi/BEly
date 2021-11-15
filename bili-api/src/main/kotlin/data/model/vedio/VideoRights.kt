package com.elouyi.bely.data.model.vedio

import kotlinx.serialization.Serializable


@Serializable
public data class VideoRights(

    /**
     * 作用尚不明确
     */
    val bp: Int,

    /**
     * 是否支持充电
     */
    val elec: Int,

    /**
     * 是否允许下载
     */
    val download: Int,

    /**
     * 是否电影
     */
    val movie: Int,

    /**
     * 是否PGC付费
     */
    val pay:  Int,

    /**
     * 是否有高码率
     */
    val hd5: Int,

    /**
     * 是否显示“禁止转载“标
     */
    val no_reprint: Int,

    /**
     * 是否自动播放
     */
    val autoplay: Int,

    /**
     * 是否UGC付费
     */
    val ugc_pay: Int,

    /**
     * 是否为互动视频
     */
    val is_stein_gate: Int,

    /**
     * 是否为联合投稿
     */
    val is_cooperation: Int,

    /**
     * 作用尚不明确
     */
    val ugc_pay_preview: Int,

    /**
     * 作用尚不明确
     */
    val no_background: Int,

    val clean_mode: Int,

    val is_360: Int,

    val no_share: Int
)
