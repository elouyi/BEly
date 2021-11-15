package com.elouyi.bely.data.model.vedio

import kotlinx.serialization.Serializable


@Serializable
public data class VideoDimension(

    /**
     * 当前分P 宽度
     */
    val width: Int,

    /**
     * 当前分P 高度
     */
    val height: Int,

    /**
     * 是否将宽高对换 0：正常, 1：对换
     */
    val rotate: Int,
)
