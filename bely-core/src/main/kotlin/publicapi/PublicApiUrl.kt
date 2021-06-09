package com.elouyi.bely.publicapi

/**
 * API 的结构地址
 */
interface PublicApiUrl {

    fun videoInfo(av: Long): String =
        "https://api.bilibili.com/x/web-interface/view?aid=$av"

    fun videoInfo(bv: String): String =
        "https://api.bilibili.com/x/web-interface/view?bvid=$bv"

    fun relation(uid: Long): String =
        "https://api.bilibili.com/x/relation/stat?vmid=$uid"

    fun masterPiece(uid: Long): String =
        "https://api.bilibili.com/x/space/masterpiece?vmid=$uid"

    fun accInfo(uid: Long): String =
        "https://api.bilibili.com/x/space/acc/info?mid=$uid"

    fun relationFollowers(
        vmid: Long,
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): String = accessKey("https://api.bilibili.com/x/relation/followers?vmid=$vmid&ps=$ps&pn=$pn")

    fun relationFollowings(
        vmid: Long,
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): String = accessKey("https://api.bilibili.com/x/relation/followings?vmid=$vmid&ps=$ps&pn=$pn",access_key)

    fun accessKey(url: String,access_key: String? = null): String {
        if (access_key == null) return url
        return buildString {
            append(url)
            append("/?access_key=$access_key")
        }
    }

    companion object : PublicApiUrl
}