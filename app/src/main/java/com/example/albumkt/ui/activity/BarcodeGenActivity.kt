package com.example.albumkt.ui.activity

import android.os.Bundle
import com.example.albumkt.R
import com.example.albumkt.util.FragmentIds

class BarcodeGenActivity : CommonActivity() {

    override fun fragmentArgs(): Bundle? {
        return null
    }

    override fun fragmentId(): Int {
        return FragmentIds.BARCODE_FEN
    }

    override fun fragmentTitleId(): Int {
        return R.string.barcode_generator
    }
}
