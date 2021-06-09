package com.elouyi.bely.biliapi.data.relation

import com.elouyi.bely.biliapi.BiliResponse
import com.elouyi.bely.biliapi.data.personal.OfficialVerify
import com.elouyi.bely.biliapi.data.personal.VipLabel
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * 用户粉丝明细回复
 */
@Serializable
data class RelationFollowerResponse(
    override val code: Int,
    override val message: String,
    override val ttl: Int,
    override val data: RelationFollowerData
) : BiliResponse<RelationFollowerData>

/**
 * 粉丝 data 对象
 * @property list 粉丝信息
 * @property re_version 尚不明确
 * @property total 粉丝总数
 */
@Serializable
data class RelationFollowerData(
    val list: List<RelationFollowerList>,
    val re_version: Long,
    val total: Int
)

/**
 * 粉丝list对象
 * @property mid 用户 uid
 * @property attribute 关注属性 0:未关注,2:已关注,6:已互粉
 * @property mtime 成为粉丝时间，时间戳，互关会刷新
 * @property tag ?
 * @property special ?
 * @property uname 用户昵称
 * @property face 用户头像 url
 * @property sign 用户签名
 * @property official_verrify 认证信息
 * @property vip 会员信息
 */
@Serializable
data class RelationFollowerList(
    val mid: Long,
    val attribute: Int,
    val mtime: Long,
    @Contextual
    val tag: Any? = null,
    val special: Int,
    val uname: String,
    val face: String,
    val sign: String,
    val official_verrify: OfficialVerify,
    val vip: FansVipInfo
)

/**
 * 粉丝 vip 对象
 * @property vipType 会员类型 0:无,1:月度,2:年度
 * @property vipDueDate 到期时间 毫秒时间戳
 * @property dueRemark 尚不明确
 * @property accessStatus 尚不明确
 * @property vipStatus 大会员状态 0:无,1:有
 * @property vipStatusWarn 尚不明确
 * @property themeType 尚不明确
 * @property label 尚不明确
 */
@Serializable
data class FansVipInfo(
    val vipType: Int,
    val vipDueDate: Long,
    val dueRemark: String,
    val accessStatus: Int,
    val vipStatus: Int,
    val vipStatusWarn: String,
    val themeType: Int,
    val label: VipLabel
)