package com.example.albumkt.ui.activity

import android.os.Bundle
import com.example.albumkt.R
import com.example.albumkt.util.Constants
import com.example.albumkt.util.FragmentIds
import com.example.albumkt.util.MediaFile

class VideoPlayActivity : CommonActivity() {
    override fun fragmentArgs(): Bundle? {
        return Bundle().apply {
            putParcelable(
                Constants.MEDIA_FILE,
                intent.getParcelableExtra<MediaFile>(Constants.MEDIA_FILE)
            )
        }
    }

    override fun fragmentId(): Int {
        return FragmentIds.VIDEO_PLAYER
    }

    override fun fragmentTitleId(): Int {
        return R.string.video_player
    }
}
