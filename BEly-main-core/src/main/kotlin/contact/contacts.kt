package com.elouyi.bely.contact

import com.elouyi.bely.security.Verification
import com.elouyi.bely.security.data.QRCodeLoginInfo
import com.elouyi.bely.security.utils.*
import com.elouyi.bely.security.utils.DedeUserID
import com.elouyi.bely.security.utils.DedeUserID__ckMd5
import com.elouyi.bely.security.utils.SESSDATA
import com.elouyi.bely.security.utils.UserCookies
import com.elouyi.bely.utils.ElyLogger
import com.elouyi.bely.utils.QRUtil
import com.elouyi.bely.utils.readLinec
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val logger = ElyLogger("contacts")

