package com.example.albumkt.util

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import com.example.albumkt.AlbumApp
import com.example.albumkt.ui.fragment.dummy.LanguageContent
import java.util.*

class MultiLanguageUtils private constructor() {
    companion object {
        @JvmStatic
        fun changeAppLanguage(item: LanguageContent.LanguageItem?) {
            if (item == null) {
                return
            }
            val ctx = AlbumApp.ins
            val resources = ctx.resources
            val metrics = resources.displayMetrics
            val configuration = resources.configuration
            setLanguage(item, configuration)
            resources.updateConfiguration(configuration, metrics)
        }

        @JvmStatic
        private fun setLanguage(item: LanguageContent.LanguageItem, configuration: Configuration) {
            val idInt = item.id.toInt()
            val locale = getLocale(idInt)

            Locale.setDefault(locale)
            LogUtils.debug("setLanguage->locale:$locale")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.setLocale(locale)
                configuration.setLocales(LocaleList(locale))
                AlbumApp.ins.createConfigurationContext(configuration)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(locale)
            } else {
                configuration.locale = locale
            }

        }

        private fun getLocale(idInt: Int): Locale {
            return when (idInt) {
                0 -> {
                    Locale.SIMPLIFIED_CHINESE
                    //Locale("zh","ZH")
                }
                1 -> {
                    Locale.TRADITIONAL_CHINESE
                    //Locale("zh","TW")
                }
                2 -> {
                    Locale.ENGLISH
                    //Locale("en","US")
                }
                else -> {
                    Locale.getDefault()
                    //Locale("zh","ZH")
                }
            }
        }

        @JvmStatic
        fun attachBaseContext(context: Context?): Context? {
            if (context == null) {
                return context
            }
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                updateResources(context)
            } else {
                context
            }
        }

        @RequiresApi(Build.VERSION_CODES.N)
        @JvmStatic
        private fun updateResources(context: Context?): Context? {
            if (context == null) {
                return context
            }

            val resources = context.resources
            val locale = getLocale(SettingsConfig.ins.getLanguageId())
            val configuration = resources.configuration
            configuration.setLocale(locale)
            configuration.setLocales(LocaleList(locale))
            return context.createConfigurationContext(configuration)
        }

    }
}