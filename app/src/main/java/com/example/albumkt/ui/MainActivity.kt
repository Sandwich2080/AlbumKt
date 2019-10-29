package com.example.albumkt.ui

import android.os.Bundle
import android.widget.FrameLayout
import butterknife.BindView
import com.example.albumkt.R
import com.example.albumkt.base.BaseActivity
import com.google.android.material.tabs.TabLayout


class MainActivity : BaseActivity() {


    @BindView(R.id.container)
    lateinit var container: FrameLayout
    @BindView(R.id.tl_bottom)
    lateinit var tlBottom: TabLayout

    lateinit var imageFragment: ImageFragment
    private fun isImageFragmentInitialized() = ::imageFragment.isInitialized

    lateinit var videoFragment: VideoFragment
    private fun isVideoFragmentInitialized() = ::videoFragment.isInitialized

    lateinit var allFragment: AllFragment
    private fun isAllFragmentInitialized() = ::allFragment.isInitialized


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)
        tlBottom = findViewById(R.id.tl_bottom)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        imageFragment = ImageFragment()
        fragmentTransaction.add(R.id.container, imageFragment)
        fragmentTransaction.commit()
        fragmentTransaction.show(imageFragment)

        val tabSelectedListener = object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                switchFragment(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                hideFragment(tab)

            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                switchFragment(tab)

            }
        }

        tlBottom.addOnTabSelectedListener(tabSelectedListener)
    }

    fun switchFragment(tab: TabLayout.Tab) {
        var pos = tab.position
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (pos) {
            0 -> {
                if (!isImageFragmentInitialized()) {
                    imageFragment = ImageFragment()
                    fragmentTransaction.add(R.id.container, imageFragment)
                }
                fragmentTransaction.show(imageFragment)
            }
            1 -> {
                if (!isVideoFragmentInitialized()) {
                    videoFragment = VideoFragment()
                    fragmentTransaction.add(R.id.container, videoFragment)
                }
                fragmentTransaction.show(videoFragment)
            }
            2 -> {

                if (!isAllFragmentInitialized()) {
                    allFragment = AllFragment()
                    fragmentTransaction.add(R.id.container, allFragment)
                }
                fragmentTransaction.show(allFragment)
            }
        }
        fragmentTransaction.commit()

    }

    fun hideFragment(tab: TabLayout.Tab) {
        var pos = tab.position
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (pos) {
            0 -> {
                if (isImageFragmentInitialized()) {
                    fragmentTransaction.hide(imageFragment)
                }
            }
            1 -> {
                if (isVideoFragmentInitialized()) {
                    fragmentTransaction.hide(videoFragment)
                }

            }
            2 -> {
                if (isImageFragmentInitialized()) {
                    fragmentTransaction.hide(allFragment)
                }
            }
        }
        fragmentTransaction.commit()
    }

}
