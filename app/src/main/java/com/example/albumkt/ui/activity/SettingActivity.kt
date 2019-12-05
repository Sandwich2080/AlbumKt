package com.example.albumkt.ui.activity

import com.example.albumkt.R
import com.example.albumkt.util.FragmentIds

class SettingActivity : CommonActivity() {
    override fun fragmentId(): Int {
        return FragmentIds.SETTING
    }

    override fun fragmentTitleId(): Int {
        return R.string.setting
    }


}
