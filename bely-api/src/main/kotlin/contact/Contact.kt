package com.elouyi.bely.contact

import com.elouyi.bely.BEly
import com.elouyi.bely.publicapi.PublicApi

sealed interface Contact {
    val uid: Long
}

val Contact.publicApi: PublicApi
    get() = BEly.publicApi