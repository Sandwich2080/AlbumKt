package com.example.albumkt

import android.app.Application

class AlbumApp:Application() {
    companion object{
        lateinit var ins:AlbumApp
    }

    override fun onCreate() {
        super.onCreate()
        ins=this
    }
}