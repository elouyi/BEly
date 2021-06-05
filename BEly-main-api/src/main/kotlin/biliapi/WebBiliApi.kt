package com.elouyi.bely.biliapi

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

    fun navDataAsync(): Deferred<NavDataResponse>

    suspend fun navData(): NavDataResponse = navDataAsync().await()
}