package com.example.albumkt.util

import android.content.Context
import android.content.SharedPreferences
import com.example.albumkt.AlbumApp

class SettingsConfig private constructor() {
    companion object {
        private val SETTING_CONFIG_SHARE = "setting_config_share"
        private val INTERNAL_PLAYER = "internal_player"
        private val LANGUAGE = "language"

        val ins = SettingsConfig()
    }

    private var settingSharePref: SharedPreferences

    init {
        settingSharePref =
            AlbumApp.ins.getSharedPreferences(SETTING_CONFIG_SHARE, Context.MODE_PRIVATE)
    }

    fun setInternalPlayer(internalPlayer: Boolean) {
        settingSharePref.edit().apply {
            putBoolean(INTERNAL_PLAYER, internalPlayer)
        }.apply()
    }

    fun isInternalPlayer(): Boolean {
        return settingSharePref.getBoolean(INTERNAL_PLAYER, true)
    }

    fun getLanguageId(): Int {
        return settingSharePref.getInt(LANGUAGE, -1)
    }

    fun setLanguage(languageId: Int) {
        settingSharePref.edit().apply {
            putInt(LANGUAGE, languageId)
        }.apply()
    }
}