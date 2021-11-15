package com.elouyi.bely.data.model.contact

import kotlinx.serialization.Serializable


@Serializable
public data class VideoOwner(

    /**
     * UP主mid
     */
    val mid: Int,

    /**
     * UP主昵称
     */
    val name: String,

    /**
     * UP主头像
     */
    val face: String
)
