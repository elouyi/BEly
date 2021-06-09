package com.elouyi.buildsrc

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