package com.elouyi.bely.data.model.vedio

import kotlinx.serialization.Serializable


@Serializable
public data class VideoDescV2(

    /**
     * 简介内容
     */
    val raw_text: String,

    /**
     * ?
     */
    val type: Int,

    /**
     * ?
     */
    val biz_id: Int
)
