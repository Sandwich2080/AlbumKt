package com.example.albumkt.ui.fragment


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.albumkt.R
import com.example.albumkt.base.BaseFragment
import com.example.albumkt.ui.activity.PreviewActivity
import com.example.albumkt.ui.adapter.FileAdapter
import com.example.albumkt.util.Constants
import com.example.albumkt.util.MediaFile
import com.example.albumkt.util.MediaLoader
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class AllFragment : BaseFragment() {

    private lateinit var gridView: GridView

    private lateinit var fileAdapter: FileAdapter

    private var imagesReady: Boolean = false

    private var videosReady: Boolean = false

    private lateinit var imageFileList: ArrayList<MediaFile>
    private fun isImageFileListInitialized() = ::imageFileList.isInitialized

    private lateinit var videoFileList: ArrayList<MediaFile>
    private fun isVideoFileListInitialized() = ::videoFileList.isInitialized

    override fun permissionsNeeded(): Array<String> {
        return arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    override fun init() {
        loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        gridView = inflater.inflate(R.layout.fragment_image, container, false) as GridView

        gridView.setOnItemClickListener { _, _, position, _ ->
            onItemClick(position)
        }

        return gridView
    }

    private fun onItemClick(position: Int) {
        val it = Intent()
        it.putParcelableArrayListExtra(Constants.FILE_LIST, fileAdapter.fileList)
        it.putExtra(Constants.CLICK_POSITION, position)
        activity?.let { act -> it.setClass(act, PreviewActivity::class.java) }
        activity?.startActivity(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadData() {
        val loader = MediaLoader()
        loader.imageLoadListener = object : MediaLoader.ImageLoadListener {
            override fun onLoadComplete(fileList: ArrayList<MediaFile>?) {
                imagesReady = true
                if (fileList != null) {
                    imageFileList = fileList
                }
                resortFileList()

            }

            override fun onCancel() {
            }
        }
        loader.loadImages(activity as Context)


        loader.videoLoadListener = object : MediaLoader.VideoLoadListener {
            override fun onLoadComplete(fileList: ArrayList<MediaFile>?) {
                videosReady = true
                if (fileList != null) {
                    videoFileList = fileList
                }
                resortFileList()
            }

            override fun onCancel() {
            }
        }
        loader.loadVideos(activity as Context)
    }

    private lateinit var resortTask: AsyncTask<Void, Void, ArrayList<MediaFile>>
    private fun isResortTaskInitialized() = ::resortTask.isInitialized
    fun resortFileList() {
        if (!imagesReady || !videosReady) {
            return
        }

        val totalList: ArrayList<MediaFile> = ArrayList()
        if (isImageFileListInitialized()) {
            totalList.addAll(imageFileList)
        }
        if (isVideoFileListInitialized()) {
            totalList.addAll(videoFileList)
        }

        resortTask = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Void, Void, ArrayList<MediaFile>>() {
            override fun doInBackground(vararg params: Void?): ArrayList<MediaFile> {
                Collections.sort(totalList, object : Comparator<MediaFile> {
                    override fun compare(o1: MediaFile?, o2: MediaFile?): Int {
                        if (o1 != null && o2 != null) {
                            return o2.dateAdded.compareTo(o1.dateAdded)
                        }
                        return 0
                    }
                })
                return totalList

            }

            override fun onPostExecute(result: ArrayList<MediaFile>?) {
                super.onPostExecute(result)
                if (result != null) {
                    fileAdapter = FileAdapter(
                        activity as Activity,
                        result
                    )
                    gridView.adapter = fileAdapter
                }
            }
        }
        resortTask.execute()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isResortTaskInitialized()) {
            resortTask.cancel(true)
        }
    }

}
