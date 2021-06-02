package com.elouyi.bely.publicapi

interface BiliResponse<out T> {

    /**
     * 状态码，正常时为 0
     */
    val code: Int

    /**
     * 信息，正常时为 "0"
     */
    val message: String

    val ttl: Int

    /**
     * 数据
     */
    val data: T
}