package com.elouyi.bely.biliapi

import com.elouyi.bely.biliapi.data.personal.AccountInfoData
import com.elouyi.bely.biliapi.data.personal.AccountInfoResponse
import com.elouyi.bely.biliapi.data.personal.RewardResponse
import com.elouyi.bely.contact.BiliBot
import com.elouyi.bely.publicapi.PublicApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

/**
 * Bilibili 需要登录的 api
 * @see [PublicApi]
 */
interface BiliApi : CoroutineScope {

    /**
     * BiliBot,可能会被删除
     */
    val bot: BiliBot

    /**
     * 获取用户信息
     * @see AccountInfoData
     */
    suspend fun getAccInfo(): AccountInfoResponse = getAccInfoAsync().await()

    fun getAccInfoAsync(): Deferred<AccountInfoResponse>

    /**
     * 每日奖励状态
     */
    fun dailyRewardAsync(): Deferred<RewardResponse>

    suspend fun dailyReward(): RewardResponse = dailyRewardAsync().await()

}