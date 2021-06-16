package com.elouyi.bely.biliapi.data.personal

import kotlinx.serialization.Serializable

/**
 * 昵称是否可用结果
 * @property code 状态码
 *  -400 请求错误
 *  -500 服务端异常
 *  0 昵称未被注册
 *  2001 昵称已被使用
 *  40002 昵称包含敏感信息
 *  40004 昵称不可包含 - _ 外的特殊字符
 *  40005 昵称过长 超过16字符
 *  40006 昵称过短 少于2字符
 *  40014 昵称已存在
 */
@Serializable
data class CheckNickNameResponse(
    val code: Int,
    val message: String?
)