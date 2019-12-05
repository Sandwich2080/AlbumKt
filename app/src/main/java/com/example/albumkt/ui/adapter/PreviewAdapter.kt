package com.example.albumkt.ui.adapter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.albumkt.ui.fragment.PreviewFragment
import com.example.albumkt.util.MediaFile

class PreviewAdapter : FragmentPagerAdapter {

    private lateinit var fileList: ArrayList<MediaFile>
    private fun isFileListInitialized() =::fileList.isInitialized

    private var frags: SparseArray<PreviewFragment> = SparseArray()

    constructor(fm: FragmentManager) : super(fm)
    constructor(fm: FragmentManager, behavior: Int) : super(fm, behavior)
    constructor(fm: FragmentManager, fileList: ArrayList<MediaFile>) : super(fm) {
        this.fileList = fileList
        notifyDataSetChanged()
    }


    override fun getItem(position: Int): Fragment {
        if (frags[position] == null) {
            frags.put(position,
                PreviewFragment.newInstance(
                    fileList[position]
                )
            )
        }
        return frags[position]
    }

    override fun getCount(): Int {
        if (isFileListInitialized()){
            return fileList.size
        }
        return 0
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}