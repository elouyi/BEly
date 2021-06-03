package com.elouyi.bely.contact

import com.elouyi.bely.security.Verification
import com.elouyi.bely.security.data.QRCodeLoginInfo
import com.elouyi.bely.security.utils.*
import com.elouyi.bely.security.utils.DedeUserID
import com.elouyi.bely.security.utils.DedeUserID__ckMd5
import com.elouyi.bely.security.utils.SESSDATA
import com.elouyi.bely.security.utils.UserCookies
import com.elouyi.bely.utils.ElyLogger
import com.elouyi.bely.utils.QRUtil
import com.elouyi.bely.utils.readLinec
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val logger = ElyLogger("contacts")

internal suspend fun loginWithQRCode(): Pair<QRCodeLoginInfo,UserCookies> {
    val codeUrl = Verification.getQRCodeLoginUrl()
    if (codeUrl.code != 0) throw Exception("get QRCode error: code: ${codeUrl.code}")
    val codeFile = QRUtil.createQRCode(codeUrl.data.url)
    logger.i("请前往\n${codeFile.absolutePath}\n扫描二维码")
    logger.i("按任意键继续")
    readLinec()
    val res = Verification.getQRCodeLoginInfo(codeUrl.data.oauthKey)
    val info = Json.decodeFromString<QRCodeLoginInfo>(res.readText())
    val headers = res.headers
    val cookies = UserCookies(
        headers[SESSDATA],
        headers[DedeUserID],
        headers[DedeUserID__ckMd5],
        headers[bili_jct]
    )
    return info to cookies
}