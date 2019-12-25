package com.example.albumkt.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.albumkt.BuildConfig
import com.example.albumkt.R
import com.example.albumkt.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : BaseFragment() {

    private lateinit var tvVersion:TextView

    override fun permissionsNeeded(): Array<String> {
        return arrayOf()
    }

    override fun init() {
        tvVersion.text = getString(R.string.version,BuildConfig.VERSION_NAME)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_about, container, false)
        tvVersion = rootView.findViewById(R.id.tv_version)
        return rootView
    }

}
