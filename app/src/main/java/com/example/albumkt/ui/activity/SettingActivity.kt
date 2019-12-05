package com.example.albumkt.ui.activity

import android.os.Bundle
import com.example.albumkt.R
import com.example.albumkt.util.FragmentIds

class SettingActivity : CommonActivity() {
    override fun fragmentArgs(): Bundle? {
        return null
    }

    override fun fragmentId(): Int {
        return FragmentIds.SETTING
    }

    override fun fragmentTitleId(): Int {
        return R.string.setting
    }


}
