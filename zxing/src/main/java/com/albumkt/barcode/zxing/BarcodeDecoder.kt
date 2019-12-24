package com.albumkt.barcode.zxing

import android.graphics.BitmapFactory
import android.os.AsyncTask
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import java.io.File
import java.lang.Exception

/**
 *  Decode QRCode or barcode from image
 */
class BarcodeDecoder private constructor() {
    companion object {

        @JvmStatic
        private fun decode(filePath: String): Result? {
            val file = File(filePath)
            if (!file.exists() || !file.isFile) {
                return null
            }
            val options = BitmapFactory.Options()
            val bitmap = BitmapFactory.decodeFile(filePath, options)

            val outWidth = options.outWidth
            val outHeight = options.outHeight

            // store the pixels
            val pixels = IntArray(outWidth * outHeight)

            bitmap.getPixels(pixels, 0, outWidth, 0, 0, outWidth, outHeight)

            val source = RGBLuminanceSource(outWidth, outHeight, pixels)

            return try {
                val binaryBitmap = BinaryBitmap(HybridBinarizer(source))
                val reader = MultiFormatReader()
                val result = reader.decode(binaryBitmap)

                bitmap.recycle()

                result
            }catch (e:Exception){
                null
            }

        }

        @JvmStatic
        fun isBarCode(result: Result):Boolean{
            return BarcodeFormat.values().contains(result.barcodeFormat)
        }

        @JvmStatic
        fun decode(filePath: String, callback: DecodeCallback) {
            DecodeTask(filePath,callback).execute()
        }

        class DecodeTask(private val filePath: String, private val callback: DecodeCallback) :
            AsyncTask<Void, Void, Result>() {

            override fun doInBackground(vararg params: Void?): Result? {
                callback.onStartDecode()
                return decode(filePath)
            }
            override fun onPostExecute(result: Result?) {
                super.onPostExecute(result)
                if (result==null){
                    callback.onDecodeResult(false,null)
                }else{
                    callback.onDecodeResult(isBarCode(result),result.text)
                }
            }

        }

    }

    interface DecodeCallback {
        fun onStartDecode()
        fun onDecodeResult(isBarcode: Boolean, resultText: String?)
    }
}