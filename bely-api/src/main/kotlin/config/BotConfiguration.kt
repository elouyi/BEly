package com.elouyi.bely.config

import com.elouyi.bely.ElyBuilder

class BotConfiguration(
    var loginMethod: LoginMethod,
    var account: String,
    var password: String
) {

    enum class LoginMethod {

        /**
         * 二维码登录
         */
        QR_CODE,

        /**
         * 短信登录
         */
        SMS,

        /**
         * 密码登录
         */
        PASSWORD
    }
    companion object {

    }
}

@Suppress("PropertyName")
class BotConfigurationBuilder : ElyBuilder<BotConfiguration> {

    var _loginMethod = BotConfiguration.LoginMethod.QR_CODE
    var _account = ""
    var _password = ""

    fun loginMethod(method: BotConfiguration.LoginMethod) {
        _loginMethod = method
    }

    fun account(account: String) {
        _account = account
    }

    fun password(password: String) {
        _password = password
    }

    override fun build(): BotConfiguration {
        return BotConfiguration(
            loginMethod = _loginMethod,
            account = _account,
            password = _password
        )
    }
}

