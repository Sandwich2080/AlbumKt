package com.example.albumkt.ui.activity

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.viewpager.widget.ViewPager
import com.example.albumkt.R
import com.example.albumkt.base.BaseActivity
import com.example.albumkt.ui.adapter.PreviewAdapter
import com.example.albumkt.util.Constants
import com.example.albumkt.util.MediaFile

class PreviewActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager

    private lateinit var previewAdapter: PreviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        viewPager = findViewById(R.id.view_pager)
        setTitle(R.string.preview_title)

        val clickPos = intent.getIntExtra(Constants.CLICK_POSITION, 0)
        val fileList: ArrayList<MediaFile> = intent.getParcelableArrayListExtra(Constants.FILE_LIST)

        previewAdapter = PreviewAdapter(
            supportFragmentManager,
            fileList
        )
        viewPager.adapter = previewAdapter

        viewPager.currentItem = clickPos

        hideSystemUI()
    }

    /**
     * Switch between fullscreen and non-fullscreen
     */
    fun switchScreen() {
        if (isFullScreen()) {
            //setNormalScreen()
            showSystemUI()
        } else {
            //setFullScreen()
            hideSystemUI()
        }
    }

    private fun isFullScreen(): Boolean {
        /*return findViewById<View>(android.R.id.content).height == resources.displayMetrics.heightPixels*/
        return viewPager.measuredHeight == resources.displayMetrics.heightPixels
    }

    private fun setFullScreen() {
        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun setNormalScreen() {
        try {
            requestWindowFeature(Window.FEATURE_ACTION_BAR)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
            )
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}
