package com.elouyi.bely.publicapi

import com.elouyi.bely.biliapi.data.relation.RelationFollowerResponse
import com.elouyi.bely.biliapi.data.relation.RelationFollowingResponse
import com.elouyi.bely.publicapi.response.*
import com.elouyi.bely.utils.ElyLogger
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

/**
 * Bilibili 无需登录的 API
 */
interface PublicApi : CoroutineScope {

    val logger: ElyLogger

    val client: HttpClient

    /// 视频信息

    /**
     * 异步获取视频信息
     * @param av 视频 av 号
     * @see VideoResponse
     * @see videoInfo
     */
    fun videoInfoAsync(av: Long): Deferred<VideoResponse>

    /**
     * 异步获取视频信息
     * @param  bv 视频 bv 号
     * @see VideoResponse
     * @see videoInfo
     */
    fun videoInfoAsync(bv: String): Deferred<VideoResponse>

    /**
     * 获取视频信息
     * @param av 视频 av 号
     * @see VideoResponse
     * @see videoInfoAsync
     */
    suspend fun videoInfo(av: Long): VideoResponse = videoInfoAsync(av).await()

    /**
     * 获取视频信息
     * @param  bv 视频 bv 号
     * @see VideoResponse
     * @see videoInfoAsync
     */
    suspend fun videoInfo(bv: String): VideoResponse = videoInfoAsync(bv).await()


    /// 用户关系

    /**
     * 异步获取用户关系信息
     * @param uid 用户 uid
     * @see RelationData
     * @see relation
     */
    fun relationAsync(uid: Long): Deferred<RelationResponse>

    /**
     * 获取用户关系信息
     * @param uid 用户 uid
     * @see RelationData
     * @see relationAsync
     */
    suspend fun relation(uid: Long): RelationResponse = relationAsync(uid).await()


    /// up主代表作


    /**
     * 异步获取 up主代表作
     * @param uid 用户 uid
     * @see VideoData
     * @see masterPiece
     */
    fun masterPieceAsync(uid: Long): Deferred<MasterPieceResponse>

    /**
     * 获取 up主代表作
     * @param uid 用户 uid
     * @see VideoData
     * @see masterPieceAsync
     */
    suspend fun masterPiece(uid: Long): MasterPieceResponse = masterPieceAsync(uid).await()

    /**
     * 账号信息
     * @param uid 用户 uid
     * @see AccInfoData
     */
    fun accInfoAsync(uid: Long): Deferred<AccInfoResponse>

    /**
     * 账号信息
     * @param uid 用户 uid
     * @see AccInfoData
     */
    suspend fun accInfo(uid: Long): AccInfoResponse = accInfoAsync(uid).await()

    /**
     * 用户粉丝查询,登录可查看自己全部，其他用户仅可查看前五页
     * @param vmid 用户 uid
     * @param access_key app登录方式必要
     * @param ps 每页项数
     * @param pn 第几页
     */
    fun relationFollowersAsync(
        vmid: Long,
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 50
    ) : Deferred<RelationFollowerResponse>

    /**
     * 用户粉丝查询,登录可查看自己全部，其他用户仅可查看前五页
     * @param vmid 用户 uid
     * @param access_key app登录方式必要
     * @param ps 每页项数
     * @param pn 第几页
     */
    suspend fun relationFollowers(
        vmid: Long,
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ) : RelationFollowerResponse = relationFollowersAsync(vmid, access_key, ps, pn).await()

    /**
     * 用户关注查询,登录可查看自己全部，其他用户仅可查看前五页
     * @param vmid 用户 uid
     * @param access_key app登录方式必要
     * @param ps 每页项数
     * @param pn 第几页
     */
    fun relationFollowingsAsync(
        vmid: Long,
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ) : Deferred<RelationFollowingResponse>

    /**
     * 用户关注查询,登录可查看自己全部，其他用户仅可查看前五页
     * @param vmid 用户 uid
     * @param access_key app登录方式必要
     * @param ps 每页项数
     * @param pn 第几页
     */
    suspend fun relationFollowings(
        vmid: Long,
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ) : RelationFollowingResponse = relationFollowingsAsync(vmid, access_key, ps, pn).await()
}