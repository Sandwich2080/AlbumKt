package com.example.albumkt

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.albumkt.ui.fragment.dummy.LanguageContent
import com.example.albumkt.util.MultiLanguageUtils
import com.example.albumkt.util.SettingsConfig

class AlbumApp : Application() {
    companion object {
        lateinit var ins: AlbumApp
    }

    override fun onCreate() {
        super.onCreate()
        ins = this
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                val id = SettingsConfig.ins.getLanguageId()
                MultiLanguageUtils.changeAppLanguage(
                    LanguageContent.LanguageItem(
                        id.toString(),
                        "",
                        ""
                    )
                )
            }

            override fun onActivityResumed(activity: Activity) {

            }

        })
    }
}