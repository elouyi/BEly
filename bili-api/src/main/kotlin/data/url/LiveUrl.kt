package com.elouyi.bely.data.url


public interface LiveUrl : UrlBase {

    public fun liveDanmuInfo(id: Int): String =
        "${https}api.live.bilibili.com/xlive/web-room/v1/index/getDanmuInfo?id=$id"

    public fun liveRoomPlay(id: Int): String =
        "${https}api.live.bilibili.com/xlive/web-room/v1/index/getRoomPlayInfo?room_id=$id"

    public companion object : LiveUrl
}