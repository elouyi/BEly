package com.elouyi.bely.biliapi

import com.elouyi.bely.publicapi.PublicApiUrl

internal interface BiliApiUrl : PublicApiUrl  {

    fun account(access_key: String? = null): String =
        accessKey("https://api.bilibili.com/x/member/web/account",access_key)

    fun reward(access_key: String? = null): String =
        accessKey("https://api.bilibili.com/x/member/web/exp/reward",access_key)

    fun exp(): String  =
        "https://www.bilibili.com/plus/account/exp.php"

    fun vipInfo(): String =
        "https://api.bilibili.com/x/vip/web/user/info"

    fun accountSecurity(access_key: String? = null): String =
        accessKey("https://passport.bilibili.com/web/site/user/info",access_key)

    fun realNameStatus(access_key: String? = null): String =
        accessKey("https://api.bilibili.com/x/member/realname/status",access_key)

    fun realNameApplyStatus(access_key: String? = null): String =
        accessKey("https://api.bilibili.com/x/member/realname/apply/status",access_key)

    fun coinLog(access_key: String? = null): String =
        accessKey("https://api.bilibili.com/x/member/web/coin/log",access_key)

    fun updateSign(access_key: String? = null): String =
        accessKey("https://api.bilibili.com/x/member/web/sign/update",access_key)

    fun exitLogin(): String =
        "https://passport.bilibili.com/login?act=exit"

    fun navData(): String =
        "https://api.bilibili.com/nav"

    fun navStat(access_key: String? = null): String =
        accessKey("https://api.bilibili.com/x/web-interface/nav/stat",access_key)

    fun getCoin(): String =
        "https://account.bilibili.com/site/getCoin"

    fun unreadMessage(): String =
        "https://api.bilibili.com/x/msgfeed/unread"

    fun unreadSingle(): String =
        "https://api.vc.bilibili.com/session_svr/v1/session_svr/single_unread"

    fun sendMessageWeb(): String =
        "https://api.vc.bilibili.com/web_im/v1/web_im/send_msg"

    fun relationFollowingSearch(
        vmid: Long,
        name: String,
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): String =
        accessKey("https://api.bilibili.com/x/relation/followings/search?vmid=$vmid&name=$name&ps=$ps&pn=$pn",access_key)

    fun relationSameFollowings(
        vmid: Long,
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): String =
        accessKey("https://api.bilibili.com/x/relation/same/followings?vmid=$vmid&&ps=$ps&pn=$pn",access_key)

    fun relationWhispers(
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): String =
        accessKey("https://api.bilibili.com/x/relation/whispers?ps=$ps&pn=$pn",access_key)

    fun relationBlacks(
        access_key: String? = null,
        ps: Int = 50,
        pn: Int = 1
    ): String =
        accessKey("https://api.bilibili.com/x/relation/blacks?ps=$ps&pn=$pn",access_key)

    fun relationModify(access_key: String? = null): String =
        accessKey("https://api.bilibili.com/x/relation/modify",access_key)

    companion object : BiliApiUrl
}