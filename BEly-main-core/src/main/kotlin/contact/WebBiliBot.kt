package com.elouyi.bely.contact

import com.elouyi.bely.publicapi.PublicApiUrl
import com.elouyi.bely.publicapi.response.AccInfoResponse
import com.elouyi.bely.security.utils.UserCookies
import com.elouyi.bely.security.utils.withUserCookies
import io.ktor.client.request.*

internal class WebBiliBot(uid: Long) : AbstractBiliBot(uid) {

    lateinit var cookies: UserCookies

    override val name: String
        get() = TODO("Not yet implemented")

    override suspend fun login() {
        logger.i("开始登录，目前登录方式为验证码登录")
        val ( _ ,cookies) = try {
            loginWithQRCode()
        } catch (e: Exception) {
            logger.e("登录失败",e)
            return
        }
        this.cookies = cookies

        // TODO: 2021/6/3 可能存在 uid 变更
    }

    override suspend fun verifyAuth() {
        ::cookies.isInitialized
        TODO("Not yet implemented")
    }

    override suspend fun getAccInfo(uid: Long): AccInfoResponse {
        return client.get(PublicApiUrl.accInfo(uid)) {
            withUserCookies(cookies)
        }
    }


}