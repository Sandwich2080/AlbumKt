package com.example.albumkt.util

import android.util.Log
import com.example.albumkt.BuildConfig

class LogUtils {
    companion object {
        private var TAG = LogUtils::class.java.simpleName
        @JvmStatic
        fun debug(log: String) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, log)
            }
        }

        @JvmStatic
        fun debug(log: String, tr: Throwable) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, log, tr)
            }
        }
    }
}