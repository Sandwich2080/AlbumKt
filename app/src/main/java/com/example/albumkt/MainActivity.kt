package com.example.albumkt

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TableLayout
import butterknife.BindView


class MainActivity : BaseActivity() {


    @BindView(R.id.container) lateinit var container:FrameLayout
    @BindView(R.id.tl_bottom) lateinit var tlBottom:TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val tvHello: TextView = findViewById(R.id.tv_hello)
        tvHello.setOnClickListener {
                Log.d("MainActivity", "")
                Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
        }*/

    }
}
