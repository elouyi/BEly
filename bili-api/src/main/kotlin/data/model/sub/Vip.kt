package com.elouyi.bely.data.model.sub

import kotlinx.serialization.Serializable


@Serializable
public data class Vip(

    /**
     * 成员会员类型 0：无, 1：月会员, 2：年会员
     */
    val type: Int,

    /**
     * 会员状态 0：无, 1：有
     */
    val status: Int,

    /**
     * 0
     */
    val  theme_type: Int
)
