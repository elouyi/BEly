package com.elouyi.bely.publicapi

/**
 * API 的结构地址
 */
object PublicApiUrl {

    fun videoInfo(av: Long): String =
        "https://api.bilibili.com/x/web-interface/view?aid=$av"

    fun videoInfo(bv: String): String =
        "https://api.bilibili.com/x/web-interface/view?bvid=$bv"

    fun relation(uid: Long): String =
        "https://api.bilibili.com/x/relation/stat?vmid=$uid"

    fun masterPiece(uid: Long): String =
        "https://api.bilibili.com/x/space/masterpiece?vmid=$uid"

    @Deprecated("this api is not public",level = DeprecationLevel.HIDDEN)
    fun userSearch(name: String): String =
        "https://api.vc.bilibili.com/dynamic_repost/v1/dynamic_repost/name_search?keyword=$name"
}