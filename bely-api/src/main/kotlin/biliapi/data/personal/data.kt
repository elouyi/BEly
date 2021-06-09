package com.elouyi.bely.biliapi.data.personal

import com.elouyi.bely.publicapi.response.UserVerify
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

/**
 * 账号安全情况 data
 * @property account_info 账号绑定信息
 * @property account_safe 密码安全信息
 * @property account_sns 互联登录绑定信息
 * @property account_other 其他信息
 */
@Serializable
data class AccountSecurityData(
    val account_info: AccountInfo,
    val account_safe: AccountSafe,
    val account_sns: AccountSns,
    val account_other: AccountOther,
)

/**
 * [AccountSecurityData] 中的 account_info 对象
 * @property hide_tel 绑定的手机号，*为隐藏部分
 * @property hide_mail 绑定邮箱，*为隐藏部分
 * @property bind_tel 是否绑定手机
 * @property bind_mail 是否绑定邮箱
 * @property tel_verify 是否验证手机号
 * @property mail_verify 是否验证邮箱
 * @property unneeded_check 是否未设置密码
 */
@Serializable
data class AccountInfo(
    val hide_tel: String,
    val hide_mail: String,
    val bind_tel: Boolean,
    val bind_mail: Boolean,
    val tel_verify: Boolean,
    val mail_verify: Boolean,
    val unneeded_check: Boolean
)

/**
 * [AccountSecurityData] 中的 account_safe 对象
 * @property Score 密码强度 0-100
 * @property pwd_level 密码强度等级 1,2,3
 * @property sequenceOf 密码是否安全
 */
@Serializable
data class AccountSafe(
    val Score: Int,
    val pwd_level: Int,
    val security: Boolean
)

/**
 * [AccountSecurityData] 中的 account_sns 对象
 * @property weibo_bind 是否绑定微博 0未绑定,1已绑定
 * @property qq_bind 是否绑定qq 0未绑定,1已绑定
 */
@Serializable
data class AccountSns(
    val weibo_bind: Int,
    val qq_bind: Int
)

/**
 * [AccountSecurityData] 中的 account_other 对象
 * @property skipVerify 尚不明确
 */
@Serializable
data class AccountOther(
    val skipVerify: Boolean
)

/**
 * 实名信息 data
 * @property status 是否已实名认证 0:未认证,1:已认证
 */
@Serializable
data class RealNameStatusData(
    val status: Int
)

/**
 * 实名详细信息 data
 * @property status 是否已实名认证 3:未认证,1:已认证
 * @property remark 驳回信息
 * @property realname 姓名 *隐藏部分
 * @property card 证件号码 *隐藏部分
 * @property card_type 证件类型
 *  1:身份证
 *  2:港澳居民来往内地通行证
 *  3:台湾居民来往大陆通行证
 *  4:护照(中国签发)
 *  5:外国人永久居留证
 *  6:其他国家或地区身份证明
 */
@Serializable
data class RealNameApplyStatusData(
    val status: Int,
    val remark: String,
    val realname: String,
    val card: String,
    val card_type: Int
)

/**
 * 硬币变化 data
 * @property list 硬币变化列表
 * @property count 变化记录数
 */
@Serializable
data class CoinLogData(
    val list: List<CoinLogObj>,
    val count: Int
)

/**
 * 硬币变化情况
 * @property time 变化时间
 * @property delta 变化量
 * @property reason 变化说明
 */
@Serializable
data class CoinLogObj(
    val time: String,
    val delta: Int,
    val reason: String
)

/**
 * 导航栏用户信息
 * @property isLogin 是否登录
 * @property email_verified 是否验证邮箱地址 0:未验证,1:已验证
 * @property face 头像url
 * @property level_info 等级信息
 * @property mid 用户 uid
 * @property mobile_verified 是否验证手机号 0:未验证,1:已验证
 * @property money 硬币数
 * @property moral 节操值
 * @property official 认证信息
 * @property officialVerify 认证信息2
 * @property pendant 头像框信息
 * @property scores 尚不明确
 * @property uname 用户昵称
 * @property vipDueDate 会员到期时间
 * @property vipStatus 会员开通状态 0:无,1:有
 * @property vipType 会员类型 0:无,1:月度,2:年度
 * @property vip_pay_type 会员开通状态 0:无,1:有
 * @property vip_avatar_subscript 是否显示会员图标 0:不显示,1:显示
 * @property vip_nickname_color 会员昵称颜色
 * @property wallet 钱包信息
 * @property has_shop 是否有推广商品
 * @property shop_url 商品推广 url
 * @property allowance_count 尚不明确
 * @property answer_status 尚不明确
 */
@Serializable
data class NavDataData(
    val isLogin: Boolean,
    val email_verified: Int,
    val face: String,
    val level_info: LevelInfo,
    val mid: Long,
    val mobile_verified: Long,
    val money: Int,
    val moral: Int,
    val official: UserVerify,
    val officialVerify: OfficialVerify,
    val pendant: Pendant,
    val scores: Int,
    val uname: String,
    val vipDueDate: Long,
    val vipStatus: Int,
    val vipType: Int,
    val vip_pay_type: Int,
    val vip_theme_type: Int,
    val vip_label: VipLabel,
    val vip_avatar_subscript: Int,
    val vip_nickname_color: String,
    val wallet: Wallet,
    val has_shop: Boolean,
    val shop_url: String,
    val allowance_count: Int,
    val answer_status: Int,
)

/**
 * [NavDataData] 中的 level_info 对象
 * @property current_level 当前等级
 * @property current_min 当前等级经验最低值
 * @property current_exp 当前经验
 * @property next_exp 升到下一级所需经验，小于六级时为数字
 */
@Serializable
data class LevelInfo(
    val current_level: Int,
    val current_min: Int,
    val current_exp: Int,
    val next_exp: String
)

/**
 * [NavDataData] 中的 Official_verify 对象
 * @property type 是否认证 -1:无,0:认证
 */
@Serializable
data class OfficialVerify(
    val type: Int,
    val desc: String
)

/**
 * [NavDataData] 中的 pendant 对象
 * @property pid 挂件 id
 * @property name 挂件名称
 * @property image 挂件图片 url
 * @property expire 尚不明确
 */
@Serializable
data class Pendant(
    val pid: Int,
    val name: String,
    val image: String,
    val expire: Int
)

/**
 * [NavDataData] 中的 vip_label 对象
 * @property path 尚不明确
 * @property text 会员名称
 * @property label_theme 会员标签
 *  vip: 大会员
 *  annual_vip：年度大会员
 *  ten_annual_vip: 十年大会员
 *  hundred_annual_vip: 百年大会员
 */
@Serializable
data class VipLabel(
    val path: String,
    val text: String,
    val label_theme: String
)

/**
 * [NavDataData] 中的 wallet 对象
 * @property mid 用户 uid
 * @property bcoin_balance 拥有硬币数
 * @property coupon_balance 每月奖励B币数
 * @property coupon_due_time 尚不明确
 */
@Serializable
data class Wallet(
    val mid: Int,
    val bcoin_balance: Int,
    val coupon_balance: Int,
    val coupon_due_time: Int
)

/**
 * 登录用户状态 data
 * @property following 关注数
 * @property follower 粉丝数
 * @property dynamic_count 发布动态数
 */
@Serializable
data class NavStatData(
    val following: Int,
    val follower: Int,
    val dynamic_count: Int
)

/**
 * 获取硬币数 data
 * @property money 硬币为正时为数字，0时为 null
 */
@Serializable
data class CoinData(
    val money: Int?
)
