package com.elouyi.bely.biliapi.data.relation

import com.elouyi.bely.biliapi.BiliResponse
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * 关注操作回复
 */
@Serializable
data class RelationModifyResponse(

    /**
     * 0: 成功, -101 账号未登录, -102 账号被封停, -111: csrf 校检失败,
     * -400: 请求错误, 22001: 不能对自己操作, 22003: 用户位于黑名单
     */
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    @Contextual
    override val data: Nothing? = null
) : BiliResponse<Nothing?>

/**
 * 操作代码
 */
enum class RelationAction(val value: Int) {

    /**
     * 关注
     */
    SUBSCRIBE(1),

    /**
     * 取关
     */
    UNSUBSCRIBE(2),

    /**
     * 悄悄关注
     */
    WHISPERSUBSCRIBE(3),

    /**
     * 取消悄悄关注
     */
    UNWHISPERSUBSCRIBE(4),

    /**
     * 拉黑
     */
    BLACK(5),

    /**
     * 取消拉黑
     */
    UNBLACK(6),

    /**
     * 提出粉丝
     */
    KICKOUTFANS(7)
}

/**
 * 关注来源
 */
enum class RelationSubscribeSrc(val value: Int) {

    /**
     * 空间
     */
    SPACE(11),

    /**
     * 视频
     */
    VIDEO(14),

    /**
     * 文章
     */
    ARTICLE(115),

    /**
     * 活动页面
     */
    ACTIVITY(22)
}