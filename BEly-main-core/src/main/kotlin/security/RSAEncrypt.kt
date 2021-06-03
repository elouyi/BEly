package com.elouyi.bely.security

import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

object RSAEncrypt {

    fun encypt(str: String,publicKey: String): String {
        val decoded = Base64.getDecoder().decode(publicKey)
        val pubKey = KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(decoded)) as RSAPublicKey

        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.ENCRYPT_MODE,pubKey)
        return Base64.getEncoder().encodeToString(cipher.doFinal(str.toByteArray()))
    }
}