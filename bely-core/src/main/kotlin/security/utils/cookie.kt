@file:Suppress("ClassName")

package com.elouyi.bely.security.utils

import com.elouyi.bely.utils.ElyLogger
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException
import java.net.URLDecoder
import kotlin.jvm.Throws

/**
 * 登录用户的 cookies
 */
@Serializable
internal open class UserCookies(
    open val sessData: String,
    open val dedeUserId: String,
    open val dedeUserIDCkMd5: String,
    open val bili_jct: String,
    open val expiresTime: Long = System.currentTimeMillis() + 29L * 24 * 60 * 60 * 1000
) {
    open val isExpires: Boolean
        get() = System.currentTimeMillis() > expiresTime

    companion object {

        fun newCookies(sessData: String,dedeUserId: Long,dedeUserIDCkMd5: String,bili_jct: String): UserCookies {
            return UserCookies(sessData,dedeUserId.toString(),dedeUserIDCkMd5, bili_jct)
        }
    }
}

internal object EmptyUserCookies : UserCookies(
    sessData = "",
    dedeUserId = "",
    dedeUserIDCkMd5 = "",
    bili_jct = "",
    expiresTime = 0
) {

    override val isExpires: Boolean = true

}


internal object UserCookieCache {

    private const val fileDir = "cache"

    init {
        File(fileDir).apply {
            if (!exists()) mkdirs()
        }
    }

    private val logger = ElyLogger("UserCookieCache")

    @Throws(IOException::class)
    suspend fun saveCookies(cookies: UserCookies) {
        val text = Json.encodeToString(cookies)
        withContext(Dispatchers.IO) {
            val file = File("$fileDir/${cookies.dedeUserId}.txt").also {
                if (!it.exists()) it.createNewFile()
            }
            file.outputStream().use {
                it.write(text.toByteArray())
            }
            logger.v("写入用户 cookie 缓存 ${cookies.dedeUserId}")
        }
    }

    suspend fun deleteCookies(cookies: UserCookies) {
        if (cookies is EmptyUserCookies) return
        val file = File("$fileDir/${cookies.dedeUserId}.txt")
        if (!file.exists()) return
        withContext(Dispatchers.IO) {
            file.delete()
        }
    }

    /**
     * 从缓存文件中读取指定 uid 的 cookie
     * @return null when cache file dose not exist or cookie expired
     * @see UserCookies
     */
    suspend fun getCookies(uid: Long): UserCookies? {
        return withContext(Dispatchers.IO) {
            val file = File("$fileDir/${uid}.txt").also {
                if (!it.exists()) return@withContext null
            }
            try {
                // wdnmd
                val str = URLDecoder.decode(file.readText(),"utf-8")
                /*
                val sb = StringBuilder()
                val buff = ByteArray(1024)
                var len = 0
                file.inputStream().use { inputstream ->
                    while (inputstream.read(buff).also { len = it } != -1) {
                        sb.append(String(buff,0,len))
                    }
                }
                val str = sb.toString()
                */
                val cookies = Json.decodeFromString<UserCookies>(str)
                if (cookies.isExpires) {
                    logger.w("$uid cookie 缓存已过期")
                    return@withContext null
                }
                cookies
            } catch (e: Exception) {
                logger.e("读取 user cookie cache 错误",e)
                null
            }
        }
    }
}

/**
 * 用于 header 取值
 */
internal sealed interface CookieProperty


private val CookieProperty.propertyName: String
    get() = javaClass.simpleName


internal object SESSDATA : CookieProperty
internal object DedeUserID : CookieProperty
internal object DedeUserID__ckMd5 : CookieProperty
internal object bili_jct : CookieProperty


internal operator fun Headers.get(property: CookieProperty): String {
    val str = toString()
    if (!str.contains(property.propertyName)) throw Exception("header 中不存在 ${property.propertyName}")
    return str.substringAfter("${property.propertyName}=")
        .substringBefore(";")
}


internal fun HttpRequestBuilder.withUserCookies(cookies: UserCookies) {
    cookie("SESSDATA",cookies.sessData)
    cookie("DedeUserID",cookies.dedeUserId)
    cookie("DedeUserID__ckMd5",cookies.dedeUserIDCkMd5)
    cookie("bili_jct",cookies.bili_jct)
}