package com.albumkt.barcode.zxing

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult


class ScanActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult: AppCompatTextView
    private lateinit var btnCopy: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        initToolBar()

        tvResult = findViewById(R.id.tv_result)
        btnCopy = findViewById(R.id.btn_copy)
        btnCopy.setOnClickListener(this)

        //IntentIntegrator(this).initiateScan()
        // A very simple tutorial on Github
        // https://github.com/journeyapps/zxing-android-embedded

        val integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    private fun initToolBar() {
        // Note that the Toolbar defined in the layout has the id "my_toolbar"
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_copy -> {
                copy()
            }
            else -> {
                //do nothing
            }
        }
    }

    private fun copy() {
        val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val content = tvResult.text.toString()
        if (!TextUtils.isEmpty(content)) {
            val data = ClipData.newPlainText(null, content)
            cm.setPrimaryClip(data)
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "The content is empty", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result: IntentResult =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result.contents != null) {
            tvResult.text = result.contents
            Toast.makeText(this, "result:" + result.contents, Toast.LENGTH_LONG).show()
        }
    }
}

