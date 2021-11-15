package com.elouyi.bely.data.model.contact

import com.elouyi.bely.data.model.sub.Official
import com.elouyi.bely.data.model.sub.Vip
import kotlinx.serialization.Serializable


@Serializable
public data class VideoStaff(

    /**
     * 成员mid
     */
    val mid: Int,

    /**
     * 成员名称
     */
    val title: String,

    /**
     * 成员昵称
     */
    val name: String,

    /**
     * 成员头像url
     */
    val face: String,

    /**
     * 成员大会员状态
     */
    val vip: Vip,

    /**
     * 成员认证信息
     */
    val official: Official,

    /**
     * 成员粉丝数
     */
    val follower: Int
)
