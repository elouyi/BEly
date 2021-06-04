package com.elouyi.bely

import com.elouyi.bely.contact.BiliBot

/**
 * 构造 [BiliBot] 的唯一方式,需要导入 BEly-core 模块
 *
 * examples:
 * ```kotlin
 * val bot = BiliBotFactory.newBot(uid){  // this: BotConfigurationBuilder
 *  ...
 * }
 * ```
 */
interface BiliBotFactory {
    companion object
}