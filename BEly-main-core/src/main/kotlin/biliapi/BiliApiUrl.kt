package com.elouyi.bely.biliapi

internal object BiliApiUrl {

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

    private fun accessKey(url: String,access_key: String? = null): String {
        if (access_key == null) return url
        return buildString {
            append(url)
            append("/?access_key=$access_key")
        }
    }
}