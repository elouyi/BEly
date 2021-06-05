package com.elouyi.bely.contact

import com.elouyi.bely.biliapi.BiliApi
import com.elouyi.bely.utils.ElyLogger
import io.ktor.client.*

interface BiliBot : Contact {

    override val uid: Long

    val name: String

    val logger: ElyLogger

    val client: HttpClient

    /**
     * Bilibili 的 api
     */
    val biliApi: BiliApi

    /**
     * 进行登录操作，重置 auth 信息，除非明确需要手动刷新，一般用 [verifyAuth]
     * @see verifyAuth
     */
    suspend fun login()

    /**
     * 验证当前 auth 信息是否可用，不可用时自动更新
     */
    suspend fun verifyAuth()

}