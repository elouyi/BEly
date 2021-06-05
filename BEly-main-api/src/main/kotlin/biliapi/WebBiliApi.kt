package com.elouyi.bely.biliapi

import com.elouyi.bely.biliapi.data.message.*
import com.elouyi.bely.biliapi.data.personal.ExpResponse
import com.elouyi.bely.biliapi.data.personal.NavDataResponse
import com.elouyi.bely.biliapi.data.personal.VipInfoResponse
import kotlinx.coroutines.Deferred

/**
 * WebBiliBot 专有的 api
 */
interface WebBiliApi : BiliApi {

    /**
     * 每日经验
     */
    fun dailyExpAsync(): Deferred<ExpResponse>

    /**
     * 每日经验
     */
    suspend fun dailyExp(): ExpResponse = dailyExpAsync().await()

    /**
     * 大会员信息
     */
    fun vipInfoAsync(): Deferred<VipInfoResponse>

    /**
     * 大会员信息
     */
    suspend fun vipInfo(): VipInfoResponse = vipInfoAsync().await()

    /**
     * 退出登录并清除 cookies
     */
    suspend fun exitLogin()

    /**
     * 导航栏信息
     */
    fun navDataAsync(): Deferred<NavDataResponse>

    /**
     * 导航栏信息
     */
    suspend fun navData(): NavDataResponse = navDataAsync().await()

    /**
     * 未读消息
     */
    fun unreadMessageAsync(): Deferred<UnreadMessageResponse>

    /**
     * 未读消息
     */
    suspend fun unreadMessage(): UnreadMessageResponse = unreadMessageAsync().await()

    /**
     * 未读私信
     */
    fun unreadSingleAsync(): Deferred<UnreadSingleResponse>

    /**
     * 未读私信
     */
    suspend fun unreadSingle(): UnreadSingleResponse = unreadSingleAsync().await()

    /**
     * 发送私信
     * @param msg 内容,发送图片时为图片 url
     * @param target 目标 uid
     * @param type 类型，文字或图片
     * @see SendMessageData.MsgType
     */
    fun sendMessageAsync(
        msg: String,
        target: Long,
        type: SendMessageData.MsgType = SendMessageData.MsgType.TEXT
    ) : Deferred<SendMessageResponse>

    /**
     * 发送私信
     * @param msg 内容,发送图片时为图片 url
     * @param target 目标 uid
     * @param type 类型，文字或图片
     * @see SendMessageData.MsgType
     */
    suspend fun sendMessage(
        msg: String,
        target: Long,
        type: SendMessageData.MsgType = SendMessageData.MsgType.TEXT
    ) : SendMessageResponse = sendMessageAsync(msg, target, type).await()

}