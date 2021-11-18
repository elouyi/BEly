package com.elouyi.bely.data.url


public interface VideoUrl : UrlBase {

    public fun videoInfo(av: Int): String =
        https + "https://api.bilibili.com/x/web-interface/view?aid=$av"

    public fun videoInfo(bv: String): String =
        https + "https://api.bilibili.com/x/web-interface/view?bvid=$bv"


    public companion object : VideoUrl
}