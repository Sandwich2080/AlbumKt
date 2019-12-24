package com.example.albumkt.ui.fragment

import com.example.albumkt.base.BaseFragment
import com.example.albumkt.util.FragmentIds

class FragmentUtil {

    companion object {
        @JvmStatic
        fun get(fragId: Int): BaseFragment? {
            return when (fragId) {
                FragmentIds.VIDEO_PLAYER -> {
                    VideoPlayerFragment()
                }
                FragmentIds.FEEDBACK -> {
                    FeedbackFragment()
                }
                FragmentIds.SETTING -> {
                    SettingFragment()
                }
                FragmentIds.BARCODE_FEN -> {
                    BarcodeGenFragment()
                }
                else -> {
                    null
                }
            }
        }
    }


}