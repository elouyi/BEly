package com.elouyi.bely.data


public interface BiliResponse<out T> {

    public val code: Int

    public val message: String

    public val ttl: Int

    public val data: T
}
