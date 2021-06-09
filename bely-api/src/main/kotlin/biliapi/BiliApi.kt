package com.elouyi.bely.biliapi

import com.elouyi.bely.biliapi.data.personal.*
import com.elouyi.bely.biliapi.data.relation.*
import com.elouyi.bely.contact.BiliBot
import com.elouyi.bely.publicapi.PublicApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

/**
 * Bilibili 需要登录的 api,此接口中的方法在 web 和 app 端都有
 *
 * [WebBiliApi] 中有 web 专用的 api
 *
 * [AppBiliApi] 中有 app 专用的 api
 *
 * [PublicApi]  不需要登录也能用的api，在不登陆的情况获取不到敏感信息
 * @see WebBiliApi
 * @see AppBiliApi
 * @see [PublicApi]
 */
interface BiliApi : PublicApi, CoroutineScope {

    /**
     * BiliBot
     */
    val bot: BiliBot


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

    /**
     * 登录用户状态
     */
    fun navStatAsync(): Deferred<NavStatResponse>

    /**
     * 登录用户状态
     */
    suspend fun navStat(): NavStatResponse = navStatAsync().await()

    /**
     * 获取硬币数
     */
    fun getCoinAsync(): Deferred<GetCoinResponse>

    /**
     * 获取硬币数
     */
    suspend fun getCoin(): GetCoinResponse = getCoinAsync().await()

    /**
     * 搜索关注明细
     * @param mid 目标用户 uid
     * @param name 搜索关键词
     * @param access_key app 端必要
     * @param ps 每页项数
     * @param pn 第几页
     */
    fun relationFollowingsSearchAsync(
        mid: Long,
        name: String,
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): Deferred<RelationFollowingsSearchResponse>

    /**
     * 搜索关注明细
     * @param mid 目标用户 uid
     * @param name 搜索关键词
     * @param access_key app 端必要
     * @param ps 每页项数
     * @param pn 第几页
     */
    suspend fun relationFollowingsSearch(
        mid: Long,
        name: String,
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): RelationFollowingsSearchResponse = relationFollowingsSearchAsync(mid, name, access_key, ps, pn).await()

    /**
     * 查询共同关注明细
     * @param vmid 目标用户uid
     * @param access_key app 端必要
     * @param ps 每页项数
     * @param pn 页码
     */
    fun relationSameFollowingsAsync(
        vmid: Long,
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): Deferred<RelationSameFollowingsResponse>

    /**
     * 查询共同关注明细
     * @param vmid 目标用户uid
     * @param access_key app 端必要
     * @param ps 每页项数
     * @param pn 页码
     */
    suspend fun relationSameFollowings(
        vmid: Long,
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): RelationSameFollowingsResponse = relationSameFollowingsAsync(vmid, access_key, ps, pn).await()

    /**
     * 查询悄悄关注
     * @param access_key app 端必要
     * @param ps 每页项数
     * @param pn 页码
     */
    fun relationWhispersAsync(
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): Deferred<RelationWhispersResponse>

    /**
     * 查询悄悄关注
     * @param access_key app 端必要
     * @param ps 每页项数
     * @param pn 页码
     */
    suspend fun relationWhispers(
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): RelationWhispersResponse = relationWhispersAsync(access_key, ps, pn).await()

    /**
     * 查询黑名单
     * @param access_key app 端必要
     * @param ps 每页项数
     * @param pn 页码
     */
    fun relationBlacksAsync(
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): Deferred<RelationBlacksResponse>

    /**
     * 查询黑名单
     * @param access_key app 端必要
     * @param ps 每页项数
     * @param pn 页码
     */
    suspend fun relationBlacks(
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): RelationBlacksResponse = relationBlacksAsync(access_key, ps, pn).await()

    /**
     * 关注用户
     * @param fid 目标用户
     * @param re_src 关注来源
     * @param access_key app 方式必要
     */
    fun relationSubscribeAsync(
        fid: Long,
        re_src: RelationSubscribeSrc = RelationSubscribeSrc.SPACE,
        access_key: String? = null
    ): Deferred<RelationModifyResponse>

    /**
     * 关注用户
     * @param fid 目标用户
     * @param re_src 关注来源
     * @param access_key app 方式必要
     */
    suspend fun relationSubscribe(
        fid: Long,
        re_src: RelationSubscribeSrc = RelationSubscribeSrc.SPACE,
        access_key: String? = null
    ): RelationModifyResponse = relationSubscribeAsync(fid, re_src, access_key).await()
}