package com.elouyi.bely.biliapi

import com.elouyi.bely.biliapi.data.personal.*
import com.elouyi.bely.contact.BiliBot
import com.elouyi.bely.publicapi.PublicApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

/**
 * Bilibili 需要登录的 api,此接口中的方法在 web 和 app 端都有
 * @see [PublicApi]
 */
interface BiliApi : CoroutineScope {

    /**
     * BiliBot,可能会被删除
     */
    val bot: BiliBot


    /// 个人中心


    /**
     * 获取用户信息
     * @see AccountInfoData
     */
    suspend fun getAccInfo(): AccountInfoResponse = getAccInfoAsync().await()

    /**
     * 获取用户信息
     * @see AccountInfoData
     */
    fun getAccInfoAsync(): Deferred<AccountInfoResponse>

    /**
     * 每日奖励状态
     * @see RewardData
     */
    fun dailyRewardAsync(): Deferred<RewardResponse>

    /**
     * 每日奖励状态
     * @see RewardData
     */
    suspend fun dailyReward(): RewardResponse = dailyRewardAsync().await()

    /**
     * 账号安全情况
     */
    fun accountSecurityAsync(): Deferred<AccountSecurityResponse>

    /**
     * 账号安全情况
     */
    suspend fun accountSecurity(): AccountSecurityResponse = accountSecurityAsync().await()

    /**
     * 账号实名认证状态
     */
    fun realNameStatusAsync(): Deferred<RealNameStatusResponse>

    /**
     * 账号实名认证状态
     */
    suspend fun realNameStatus(): RealNameStatusResponse = realNameStatusAsync().await()

    /**
     * 账号详细实名信息
     */
    fun realNameApplyStatusAsync(): Deferred<RealNameApplyStatusResponse>

    /**
     * 账号详细实名信息
     */
    suspend fun realNameApplyStatus(): RealNameApplyStatusResponse = realNameApplyStatusAsync().await()

    /**
     * 硬币变化情况
     */
    fun coinLogAsync(): Deferred<CoinLogResponse>

    /**
     * 硬币变化情况
     */
    suspend fun coinLog(): CoinLogResponse = coinLogAsync().await()

    /**
     * 修改签名
     * @param sign 签名内容
     */
    fun updateSignAsync(sign: String): Deferred<UpdateSignResponse>

    /**
     * 修改签名
     * @param sign 签名内容
     */
    suspend fun updateSign(sign: String): UpdateSignResponse = updateSignAsync(sign).await()


    /// 个人中心 完


    /// 
}