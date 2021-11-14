package com.elouyi.bely.biliapi.data.live

import kotlinx.serialization.Serializable

@Serializable
data class FansCard(
    val level: Int,
    val cardName: String,
    val anchorName: String,
    val anchorUid: Long,
    val roomId: Int
)