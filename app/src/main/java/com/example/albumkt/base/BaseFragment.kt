package com.example.albumkt.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import pub.devrel.easypermissions.EasyPermissions

abstract class BaseFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults,this)
    }

    abstract fun permissionsNeeded(): Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
    }

    private fun requestPermissions() {
        val permissions = permissionsNeeded()
        if (EasyPermissions.hasPermissions(context!!, *permissions)) {
            init()
        } else {
            EasyPermissions.requestPermissions(this,"Permission Request",hashCode(),*permissions)
        }
    }

    // Do init operations
    abstract fun init()

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        for (perm in perms){
            Toast.makeText(context, "Permission[$perm] denied!",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        init()
    }

}