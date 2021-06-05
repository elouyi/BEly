package com.elouyi.bely.biliapi

internal object BiliApiUrl {

    fun account(access_key: String? = null): String {
        return accessKey("https://api.bilibili.com/x/member/web/account",access_key)
    }

    fun reward(access_key: String? = null): String {
        return accessKey("https://api.bilibili.com/x/member/web/exp/reward",access_key)
    }

    private fun accessKey(url: String,access_key: String? = null): String {
        if (access_key == null) return url
        return buildString {
            append(url)
            append("/?access_key=$access_key")
        }
    }
}