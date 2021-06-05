package com.elouyi.bely.biliapi.data.personal

import kotlinx.serialization.Serializable

/**
 * 用户信息 data
 * @property mid uid
 * @property uname 昵称
 * @property userid 用户名
 * @property sign 签名
 * @property birthday 生日
 * @property sex 性别
 * @property nick_free 是否设置昵称
 * @property rank 会员等级
 */
@Serializable
data class AccountInfoData(
    val mid: String,
    val uname: String,
    val userid: String,
    val sign: String,
    val birthday: String,
    val sex: String,
    val nick_free: Boolean,
    val rank: String
)

/**
 * 每日状态
 * @property login 每日登录
 * @property watch 每日观看
 * @property coins 每日获得硬币，上限为 50 该值更新存在延迟
 * @property share 每日分享
 * @property email 是否绑定邮箱
 * @property tel 是否绑定手机号
 * @property safe_question 是否设置密保问题
 * @property identify_card 是否实名认证
 */
@Serializable
data class RewardData(
    val login: Boolean,
    val watch: Boolean,
    val coins: Int,
    val share: Boolean,
    val email: Boolean,
    val tel: Boolean,
    val safe_question: Boolean,
    val identify_card: Boolean
)

/**
 * 大会员信息
 * @property mid uid
 * @property vip_pay_type 大会员类型 0:无，1:月度，2:年度
 * @property vip_due_date 大会员到期时间 毫秒时间戳
 * @property vip_pay_type 是否已购买大会员 0:未购买，1:已购买
 * @property theme_type 尚不明确
 */
@Serializable
data class VipInfoData(
    val mid: Long,
    val vip_type: Int,
    val vip_due_date: Long,
    val vip_pay_type: Long,
    val theme_type: Int,
)
