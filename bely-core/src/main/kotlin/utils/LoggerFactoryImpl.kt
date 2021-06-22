package com.elouyi.bely.utils

object LoggerFactoryImpl : LoggerFactory {
    override fun newLogger(owner: String): ElyLogger = YwLogger(owner)
}