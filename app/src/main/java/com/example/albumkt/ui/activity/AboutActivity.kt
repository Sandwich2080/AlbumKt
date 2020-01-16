package com.example.albumkt.ui.activity

import android.os.Bundle
import com.example.albumkt.R
import com.example.albumkt.util.FragmentIds

class AboutActivity : CommonActivity() {

    override fun fragmentArgs(): Bundle? {
        return null
    }

    override fun fragmentId(): Int {
        return FragmentIds.ABOUT
    }

    override fun fragmentTitleId(): Int {
        return R.string.about
    }
}
