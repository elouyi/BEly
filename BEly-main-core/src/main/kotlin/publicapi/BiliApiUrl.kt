package com.elouyi.bely.publicapi

object BiliApiUrl {

    fun videoInfo(av: Long): String =
        "https://api.bilibili.com/x/web-interface/view?aid=$av"

    fun videoInfo(bv: String): String =
        "https://api.bilibili.com/x/web-interface/view?bvid=$bv"
}