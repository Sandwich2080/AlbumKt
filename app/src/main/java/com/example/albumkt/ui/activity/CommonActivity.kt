package com.example.albumkt.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.albumkt.R
import com.example.albumkt.base.BaseActivity
import com.example.albumkt.ui.fragment.FragmentUtil

abstract class CommonActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        setupActionBar()
        setupContainer()
    }

    private fun setupActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        // should be called before #setSupportActionBar, if not, it'll not work
        toolbar.setTitle(fragmentTitleId()) // works well
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        //supportActionBar?.setTitle(fragmentTitleId()) //works well
    }

    abstract fun fragmentArgs():Bundle?

    private fun setupContainer() {
        val contentFrag = FragmentUtil.get(fragmentId())

        if (contentFrag != null) {
            contentFrag.arguments = fragmentArgs()
            supportFragmentManager.beginTransaction().apply {
                add(R.id.container, contentFrag)
                show(contentFrag)
                commit()
            }
        }
    }

    abstract fun fragmentId(): Int
    abstract fun fragmentTitleId(): Int
}
