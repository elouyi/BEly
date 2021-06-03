package com.elouyi.bely.utils

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.google.zxing.qrcode.encoder.QRCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.imageio.ImageIO


object QRUtil {

    /**
     * 生成二维码
     * @param text 二维码信息
     * @param size 二维码大小
     * @return 二维码文件路径
     */
    suspend fun createQRCode(text: String,size: Int = 300): String {
        return withContext(Dispatchers.IO) {
            val hints = mutableMapOf<EncodeHintType,Any>()
            hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
            hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
            hints[EncodeHintType.MARGIN] = 1
            val bitMatrix = MultiFormatWriter().encode(text,BarcodeFormat.QR_CODE,size,size,hints)
            val bi = MatrixToImageWriter.toBufferedImage(bitMatrix)
            val file = File("qrcode.jpg")
            ImageIO.write(bi,"JPEG",file)
            file.absolutePath
        }
    }
}