package com.elouyi.bely.contact

import com.elouyi.bely.biliapi.WebBiliApi
import com.elouyi.bely.biliapi.WebBiliApiImpl
import com.elouyi.bely.config.BotConfiguration
import com.elouyi.bely.security.Verification
import com.elouyi.bely.security.utils.*
import com.elouyi.bely.security.utils.DedeUserID
import com.elouyi.bely.security.utils.DedeUserID__ckMd5
import com.elouyi.bely.security.utils.SESSDATA
import com.elouyi.bely.security.utils.UserCookies
import com.elouyi.bely.security.utils.bili_jct
import com.elouyi.bely.utils.QRUtil
import com.elouyi.bely.utils.readLinec
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

internal class WebBiliBotImpl(
    uid: Long,
    config: BotConfiguration
) : AbstractBiliBot(uid,config),WebBiliBot {

    init {
        runBlocking {
            val c = UserCookieCache.getCookies(uid)
            if (c == null) {
                logger.i("未找到 cookie 缓存，开始登录 ...")
                login()
            } else {
                cookies = c
            }
        }
    }

    override val biliApi: WebBiliApi = WebBiliApiImpl(this)

    override val name: String by lazy {
        "Not yet implemented"
    }

    var cookies: UserCookies
        private set

    override suspend fun login() {
        val c: UserCookies? = when(config.loginMethod) {

            BotConfiguration.LoginMethod.QR_CODE -> loginWithQRCode()

            BotConfiguration.LoginMethod.PASSWORD -> TODO()

            BotConfiguration.LoginMethod.SMS -> TODO()

        }
        if (c != null) {
            logger.i("登陆成功")
            cookies = c
            UserCookieCache.saveCookies(c)
        } else {
            logger.w("登录失败")
        }


    }

    override suspend fun verifyAuth() {
        if (!cookies.isExpires) return
        val c = UserCookieCache.getCookies(uid)
        if (c != null) {
            if (!c.isExpires) {
                cookies = c
                return
            }
        }
        login()
    }


    override suspend fun loginWithQRCode(): UserCookies? {
        try {
            logger.i("开始登录，目前登录方式为验证码登录")
            val codeUrl = Verification.getQRCodeLoginUrl()
            if (codeUrl.code != 0) throw Exception("get QRCode error: code: ${codeUrl.code}")
            val codeFile = QRUtil.createQRCode(codeUrl.data.url,"qrcode$uid.jpg")
            logger.i("请前往\n${codeFile.absolutePath}\n扫描二维码")
            logger.i("按任意键继续")
            try {
                withTimeout(180L * 1000) {
                    readLinec()
                }
            } catch (e: Exception) {
                logger.e("登录超时")
                return null
            }

            val res = Verification.getQRCodeLoginInfo(codeUrl.data.oauthKey)
            //val info = Json.decodeFromString<QRCodeLoginInfo>(res.readText())
            val headers = res.headers
            val cookies = UserCookies(
                headers[SESSDATA],
                headers[DedeUserID],
                headers[DedeUserID__ckMd5],
                headers[bili_jct]
            )
            return if (cookies.dedeUserId != uid.toString()) {
                logger.e("登录失败: 扫码用户与 Bot uid 不一致\nbot: $uid\nuser: ${cookies?.dedeUserId}")
                null
            } else cookies
        } catch (e: Exception) {
            logger.e("登陆失败",e)
            return null
        }

    }


}