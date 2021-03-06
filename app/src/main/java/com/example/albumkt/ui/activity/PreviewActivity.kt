package com.example.albumkt.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager.widget.ViewPager
import com.example.albumkt.R
import com.example.albumkt.base.BaseActivity
import com.example.albumkt.common.PreviewData
import com.example.albumkt.ui.adapter.PreviewAdapter
import com.example.albumkt.util.Constants
import com.example.albumkt.util.LogUtils
import com.example.albumkt.util.MediaFile

class PreviewActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager

    private lateinit var previewAdapter: PreviewAdapter

    init {
      LogUtils.debug("$this init")
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        LogUtils.debug("$this 1")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.debug("$this 2")
        setContentView(R.layout.activity_preview)
        viewPager = findViewById(R.id.view_pager)
        setTitle(R.string.preview_title)

        val clickPos = intent.getIntExtra(Constants.CLICK_POSITION, 0)
        val fileList: ArrayList<MediaFile>? = PreviewData.fileList//intent.getParcelableArrayListExtra(Constants.FILE_LIST)

        previewAdapter = PreviewAdapter(
            supportFragmentManager,
            fileList!!
        )
        viewPager.adapter = previewAdapter

        viewPager.currentItem = clickPos

        val tvIndicator = findViewById<AppCompatTextView>(R.id.tv_indicator)
        tvIndicator.apply {
            text = String.format("%d/%d", clickPos+1, fileList.size)
        }

        viewPager.apply {

            addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    tvIndicator.text = String.format("%d/%d", position+1, fileList.size)
                }

            })
        }

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
        } catch (e: Exception) {
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
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
