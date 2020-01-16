package com.example.albumkt

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import android.os.Bundle
import com.example.albumkt.ui.fragment.dummy.LanguageContent
import com.example.albumkt.util.ActivityStack
import com.example.albumkt.util.LogUtils
import com.example.albumkt.util.MultiLanguageUtils
import com.example.albumkt.util.SettingsConfig

class AlbumApp() : Application() {
    companion object {
        lateinit var ins: AlbumApp
    }

    init {
        ins = this
    }

    override fun onCreate() {
        super.onCreate()
        changeLanguage()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                ActivityStack.ins.pop()
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                ActivityStack.ins.push(activity)
                LogUtils.debug("$activity created.")
                changeLanguage()
            }

            override fun onActivityResumed(activity: Activity) {

            }

        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        changeLanguage()
    }

    private fun changeLanguage() {
        val id = SettingsConfig.ins.getLanguageId()
        LogUtils.debug("languageId:$id")
        MultiLanguageUtils.changeAppLanguage(
            LanguageContent.LanguageItem(
                id.toString(),
                "",
                ""
            )
        )
    }
}