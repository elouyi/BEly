package com.elouyi.bely.secret.cookie

import kotlinx.serialization.Serializable

@Serializable
public data class UserCookie(
    val sessData: String,
    val dedeUserId: String,
    val dedeUserIDCkMd5: String,
    val bili_jct: String,
) {

    public companion object {
        public val EmptyCookie: UserCookie = UserCookie("", "", "", "")
    }
}
