package com.example.albumkt.ui.activity

import com.example.albumkt.R
import com.example.albumkt.util.FragmentIds

class FeedbackActivity : CommonActivity() {

    override fun fragmentId(): Int {
        return FragmentIds.FEEDBACK
    }

    override fun fragmentTitleId(): Int {
        return R.string.feedback
    }
}
