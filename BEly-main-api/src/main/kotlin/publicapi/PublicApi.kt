package com.elouyi.bely.publicapi

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

    fun accInfoAsync(uid: Long): Deferred<AccInfoResponse>

    suspend fun accInfo(uid: Long): AccInfoResponse = accInfoAsync(uid).await()

}