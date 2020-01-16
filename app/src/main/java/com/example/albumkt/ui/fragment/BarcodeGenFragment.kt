package com.example.albumkt.ui.fragment

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.albumkt.barcode.zxing.BarcodeEncoder
import com.example.albumkt.R
import com.example.albumkt.base.BaseFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.io.FileOutputStream

class BarcodeGenFragment : BaseFragment(), View.OnClickListener {

    private lateinit var etContent: TextInputEditText
    private lateinit var btnGen: MaterialButton
    private lateinit var ivQRCodeGen: AppCompatImageView
    private lateinit var llGenCode: LinearLayout
    private lateinit var btnSave: MaterialButton

    override fun permissionsNeeded(): Array<String> {
        return arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    override fun init() {
        btnGen.setOnClickListener(this)
        btnSave.setOnClickListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_barcode_gen, container, false)

        etContent = rootView.findViewById(R.id.et_content)
        btnGen = rootView.findViewById(R.id.btn_gen)
        ivQRCodeGen = rootView.findViewById(R.id.iv_qr_code)
        llGenCode = rootView.findViewById(R.id.ll_gen_code)
        btnSave = rootView.findViewById(R.id.btn_save)
        return rootView
    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }

        when (v.id) {
            R.id.btn_gen -> {
                generate()
            }
            R.id.btn_save -> {
                save()
            }
            else -> {
            }
        }
    }

    private lateinit var bitmap: Bitmap

    private fun generate() {
        val text = etContent.text
        if (text.isNullOrEmpty()) {
            return
        }
        bitmap = BarcodeEncoder.encode2QRCode(text.toString())
        ivQRCodeGen.setImageBitmap(bitmap)
        llGenCode.visibility = View.VISIBLE

    }

    private fun save() {
        val externalFilesDir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (externalFilesDir != null) {

            val fileName =
                StringBuilder().append(System.currentTimeMillis()).append(".png").toString()
            val file = File(externalFilesDir, fileName)
            if (file.isFile && file.exists()) {
                file.delete()
                file.createNewFile()
            }
            when (bitmap.compress(Bitmap.CompressFormat.PNG, 100, FileOutputStream(file))) {
                true -> {
                    addToGallery(file.absolutePath, fileName)
                    Toast.makeText(
                        context,
                        String.format("Saved to[%s]", externalFilesDir.absolutePath),
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    Toast.makeText(context, "Save failed", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "No External storage", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * If this method not invoked, the picture can't be seen in gallery.
     */
    private fun addToGallery(filePath: String, name: String) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.insertImage(context?.contentResolver, filePath, name, "")
        } else {
            MediaStore.setIncludePending(Uri.fromFile(File(filePath)))
        }
    }
}