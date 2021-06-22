package com.elouyi.bely.biliapi.data.live

sealed interface LiveRoomEvent

// TODO: 2021/6/22 data 换成实体类

class DanmuMsg(val data: String) : LiveRoomEvent
class SendGift(val data: String) : LiveRoomEvent
class ComboSend(val data: String) : LiveRoomEvent
class RoomRealTimeMessageUpdate(data: String) : LiveRoomEvent
class SuperChatEntrance(val data: String) : LiveRoomEvent
class OnlineRankCount(val data: String) : LiveRoomEvent
class StopLiveRoomList(val data: String) : LiveRoomEvent
class HeartBeatResponse(val population: Int) : LiveRoomEvent
class OnError(val t: Throwable) : LiveRoomEvent

@JvmInline
value class UnknownEvent(val data: String) : LiveRoomEvent