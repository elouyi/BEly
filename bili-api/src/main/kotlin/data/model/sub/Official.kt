package com.elouyi.bely.data.model.sub

import kotlinx.serialization.Serializable


@Serializable
public data class Official(

    /**
     * 成员认证级别:
     * 0：无,
     * 1 2 7：个人认证,
     * 3 4 5 6：机构认证
     */
    val role: Int,

    /**
     * 成员认证名
     */
    val title: String,

    /**
     * 成员认证备注
     */
    val desc: String,

    /**
     * 成员认证类型:
     * -1：无,
     * 0：有
     */
    val type: Int
)
