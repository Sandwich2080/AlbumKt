package com.example.albumkt.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.widget.SwitchCompat

import com.example.albumkt.R
import com.example.albumkt.base.BaseFragment
import com.example.albumkt.util.SettingsConfig

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var internalPlayerSwitch: SwitchCompat

    override fun permissionsNeeded(): Array<String> {
        return arrayOf()
    }

    override fun init() {
        internalPlayerSwitch.isChecked = SettingsConfig.ins.isInternalPlayer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_setting, container, false)
        internalPlayerSwitch = rootView.findViewById<SwitchCompat>(R.id.internal_player_switch)
        internalPlayerSwitch.setOnCheckedChangeListener { _, isChecked ->
            SettingsConfig.ins.setInternalPlayer(isChecked)
        }
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
