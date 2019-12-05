package com.example.albumkt.ui.activity

import com.example.albumkt.R
import com.example.albumkt.util.FragmentIds

class VideoPlayActivity : CommonActivity() {

    override fun fragmentId(): Int {
        return FragmentIds.VIDEO_PLAYER
    }

    override fun fragmentTitleId(): Int {
        return R.string.video_player
    }
}
