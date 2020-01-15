package com.example.albumkt.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.albumkt.R
import com.example.albumkt.base.BaseFragment

import com.example.albumkt.ui.fragment.dummy.LanguageContent
import com.example.albumkt.ui.fragment.dummy.LanguageContent.LanguageItem
import com.example.albumkt.util.MultiLanguageUtils
import com.example.albumkt.util.SettingsConfig

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [LanguageSelectFragment.OnListFragmentInteractionListener] interface.
 */
class LanguageSelectFragment : BaseFragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_language_select_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                listener = object : OnListFragmentInteractionListener {
                    override fun onListFragmentInteraction(item: LanguageItem?) {
                        onListItemClick(item)
                    }
                }
                adapter = LanguageItemRecyclerViewAdapter(LanguageContent.ITEMS, listener)
            }
        }
        return view
    }

    private fun onListItemClick(item: LanguageItem?) {
        //MultiLanguageUtils.changeAppLanguage(item)
        if (item == null) {
            return
        }
        SettingsConfig.ins.setLanguage(item.id.toInt())
        Toast.makeText(context, R.string.no_recognized_result, Toast.LENGTH_SHORT).show()
    }

    override fun permissionsNeeded(): Array<String> {
        return arrayOf()
    }

    override fun init() {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        /*if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }*/
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: LanguageItem?)
    }

    companion object {

        // Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            LanguageSelectFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
