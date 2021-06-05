package com.elouyi.bely.biliapi

import com.elouyi.bely.biliapi.data.personal.ExpResponse
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

    suspend fun dailyExp(): ExpResponse = dailyExpAsync().await()

    fun vipInfoAsync(): Deferred<VipInfoResponse>

    suspend fun vipInfo(): VipInfoResponse = vipInfoAsync().await()
}