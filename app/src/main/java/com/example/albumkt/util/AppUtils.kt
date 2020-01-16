package com.example.albumkt.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Process
import com.example.albumkt.AlbumApp

class AppUtils private constructor() {

    companion object {

        private const val REQUEST_CODE_RESTART = 0xa1

        @JvmStatic
        fun restart() {
            // Get the launcher intent of this app.
            val ctx = AlbumApp.ins
            val packageManager = ctx.packageManager
            val launchIntent = packageManager.getLaunchIntentForPackage(ctx.packageName)

            // Restart the app after a specified time.
            val pendingIntent = PendingIntent.getActivity(
                ctx,
                REQUEST_CODE_RESTART,
                launchIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
            val alarmManager = ctx.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent)

            // Kill current process
            Process.killProcess(Process.myPid())
        }

    }
}