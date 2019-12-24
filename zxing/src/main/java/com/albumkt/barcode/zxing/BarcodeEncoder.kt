package com.albumkt.barcode.zxing

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter

class BarcodeEncoder private constructor() {

    companion object {
        /**
         * Encode text content to QRCode
         * @param content The text content to encode
         */
        @JvmStatic
        fun encode2QRCode(content: String): Bitmap {
            val multiFormatWriter = MultiFormatWriter()
            val bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 1024, 1024)

            val width = bitMatrix.width
            val height = bitMatrix.height
            val pixels = IntArray(width*height)

            for (y in 0 until height) {
                val offset = y * width
                for (x in 0 until width) {
                    pixels[x + offset] = when (bitMatrix.get(x, y)) {
                        true -> {
                            Color.BLACK
                        }
                        else -> {
                            Color.WHITE
                        }
                    }
                }
            }

            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height)

            return bitmap
        }


    }

}