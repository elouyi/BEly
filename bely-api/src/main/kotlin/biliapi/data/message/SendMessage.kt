package com.elouyi.bely.biliapi.data.message

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Serializable

@Serializable
data class SendMessageResponse(
    override val code: Int,
    override val message: String,
    override val data: SendMessageData,
    override val ttl: Int
) : BiliResponse<SendMessageData>

@Serializable
data class SendMessageData(
    val msg_key: Long,
    val _gt_: Int,
) {
    /**
     * 发送私信的类型
     */
    enum class MsgType {

        /**
         * 文字
         */
        TEXT,

        /**
         * 图片
         */
        PICTURE
    }
}


/**
 * 图片消息
 * @param url 图片 url
 */
fun buildPictureMsg(url: String): String {
    return "{\"url\":\"$url\"}"
}