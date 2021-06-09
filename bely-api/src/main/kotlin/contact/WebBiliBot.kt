package com.elouyi.bely.contact

import com.elouyi.bely.biliapi.WebBiliApi

interface WebBiliBot : BiliBot {

    override val biliApi: WebBiliApi
}