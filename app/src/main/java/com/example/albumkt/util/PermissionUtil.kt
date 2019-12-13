package com.example.albumkt.util

import android.content.Context
import android.content.pm.PackageManager

class PermissionUtil private constructor() {

    companion object{

        @JvmStatic
        fun getPermissionsDeclared(ctx:Context):Array<out String>{
            val packageInfo =
                ctx.packageManager.getPackageInfo(ctx.packageName, PackageManager.GET_PERMISSIONS)
            return packageInfo.requestedPermissions
        }

    }


}