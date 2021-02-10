package com.example.albumkt.ui.fragment


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.albumkt.R
import com.example.albumkt.base.BaseFragment
import com.example.albumkt.common.PreviewData
import com.example.albumkt.ui.activity.PreviewActivity
import com.example.albumkt.ui.adapter.FileAdapter
import com.example.albumkt.util.Constants
import com.example.albumkt.util.MediaFile
import com.example.albumkt.util.MediaLoader

/**
 * A simple [Fragment] subclass.
 */
class VideoFragment : BaseFragment() {

    private lateinit var gridView: GridView

    private lateinit var fileAdapter: FileAdapter

    override fun init() {
        loadData()
    }

    override fun permissionsNeeded(): Array<String> {
        return arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        gridView = inflater.inflate(R.layout.fragment_video, container, false) as GridView

        gridView.setOnItemClickListener { _, _, position, _ ->
            onItemClick(position)
        }

        return gridView
    }

    private fun onItemClick(position: Int) {
        val it = Intent()
        //it.putParcelableArrayListExtra(Constants.FILE_LIST, fileAdapter.fileList)
        PreviewData.fileList = fileAdapter.fileList
        it.putExtra(Constants.CLICK_POSITION, position)
        activity?.let { act -> it.setClass(act, PreviewActivity::class.java) }
        activity?.startActivity(it)
    }

    private fun loadData() {
        val loader = MediaLoader()
        loader.videoLoadListener = object : MediaLoader.VideoLoadListener {
            override fun onLoadComplete(fileList: ArrayList<MediaFile>?) {
                if (fileList != null) {
                    fileAdapter = FileAdapter(
                        activity as Activity,
                        fileList
                    )
                    gridView.adapter = fileAdapter
                }
            }

            override fun onCancel() {
            }
        }
        loader.loadVideos(activity as Context)
    }
}
