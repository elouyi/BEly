package com.elouyi.bely.contact

import com.elouyi.bely.BEly
import com.elouyi.bely.publicapi.response.AccInfoResponse
import com.elouyi.bely.publicapi.response.MasterPieceResponse
import com.elouyi.bely.publicapi.response.RelationResponse

sealed interface Contact {
    val uid: Long


    /// 一下方法默认为不带认证的请求，拿不到隐私信息，如需要则重写

    suspend fun getRelation(uid: Long = this.uid): RelationResponse =
        BEly.publicApi.relation(uid)

    suspend fun getMasterPiece(uid: Long = this.uid): MasterPieceResponse =
        BEly.publicApi.masterPiece(uid)

    suspend fun getAccInfo(uid: Long = this.uid): AccInfoResponse =
        BEly.publicApi.accInfo(uid)
}

