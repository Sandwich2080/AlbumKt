package com.example.albumkt.base

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import pub.devrel.easypermissions.EasyPermissions

abstract class BaseFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    companion object{
        const val PERMISSION_REQUEST_CODE = 0xff
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    abstract fun permissionsNeeded(): Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
    }

    /**
     * Dynamically request runtime permission
     */
    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            init()
            return
        }

        val permissions = permissionsNeeded()
        if (permissions.isEmpty() || EasyPermissions.hasPermissions(context!!, *permissions)) {
            init()
        } else {
            EasyPermissions.requestPermissions(this, "Permission Request", PERMISSION_REQUEST_CODE, *permissions)
        }
    }

    /**
     * Do init operations after runtime permission granted.
     */
    abstract fun init()

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        for (perm in perms) {
            Toast.makeText(context, "Permission[$perm] denied!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        init()
    }

}


