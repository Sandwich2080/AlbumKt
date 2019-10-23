package com.example.albumkt.ui

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TableLayout
import butterknife.BindView
import com.example.albumkt.R
import com.example.albumkt.base.BaseActivity


class MainActivity : BaseActivity() {


    @BindView(R.id.container) lateinit var container:FrameLayout
    @BindView(R.id.tl_bottom) lateinit var tlBottom:TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val imageFragment = ImageFragment()
        fragmentTransaction.add(R.id.container,imageFragment)
        fragmentTransaction.commit()
        fragmentTransaction.show(imageFragment)
        //fragmentTransaction.setMaxLifecycle(imageFragment,Lifecycle.State.CREATED)

    }
}
