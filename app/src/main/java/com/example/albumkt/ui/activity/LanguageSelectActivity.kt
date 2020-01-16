package com.example.albumkt.ui.activity

import android.os.Bundle
import com.example.albumkt.R
import com.example.albumkt.util.FragmentIds

class LanguageSelectActivity : CommonActivity() {

    override fun fragmentArgs(): Bundle? {
        return null
    }

    override fun fragmentId(): Int {
        return FragmentIds.LANGUAGE_SELECT
    }

    override fun fragmentTitleId(): Int {
        return R.string.language_setting
    }
}
