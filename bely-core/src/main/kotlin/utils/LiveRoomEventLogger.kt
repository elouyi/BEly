package com.elouyi.bely.utils

/**
 * 直播间事件 logger，直播间弹幕，礼物等事件
 */
internal class LiveRoomEventLogger(owner: String) : YwLogger(owner) {

    // TODO: 2021/6/22 颜色
    // 默认等级为 debug，不输出心跳包的 verbose 事件
    //override var level: LogLevel = LogLevel.DEBUG

}