package com.example.albumkt.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.albumkt.R
import com.example.albumkt.base.BaseFragment
import com.example.albumkt.common.PreviewData
import com.example.albumkt.ui.activity.PreviewActivity
import com.example.albumkt.ui.adapter.FileAdapter
import com.example.albumkt.util.Constants
import com.example.albumkt.util.LogUtils
import com.example.albumkt.util.MediaFile
import com.example.albumkt.util.MediaLoader

//  Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ImageFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class ImageFragment : BaseFragment() {
    // Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var gridView: GridView

    private lateinit var fileAdapter: FileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

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
        gridView = inflater.inflate(R.layout.fragment_image, container, false) as GridView
        gridView.setOnItemClickListener { _, _, position, _ ->
            onItemClick(position)
        }

        val gridViewParent = gridView.rootView
        gridViewParent.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                gridViewParent.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val with = gridViewParent.measuredWidth
                val height = gridViewParent.measuredHeight
                LogUtils.debug("parent:width->$with,height->$height")
            }

        })

        return gridView
    }

    private fun onItemClick(position: Int) {
        try {
            val it = Intent(activity as Context,PreviewActivity::class.java).apply {
                // If passing a big list with Intent, this may cause memory issue. Then the app may crash. For detail, see the following link
                // https://stackoverflow.com/questions/57848280/handlewindowvisibility-no-activity-for-token-android-os-binderproxy
                //putParcelableArrayListExtra(Constants.FILE_LIST, fileAdapter.fileList)
                PreviewData.fileList = fileAdapter.fileList
                putExtra(Constants.CLICK_POSITION, position)
            }
            LogUtils.debug("click to preview: position->$position,$activity")
            activity?.startActivity(it)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun loadData() {
        val loader = MediaLoader()
        loader.imageLoadListener = object : MediaLoader.ImageLoadListener {
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
        loader.loadImages(activity as Context)
    }

    // Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        private val TAG: String = ImageFragment::class.java.simpleName
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ImageFragment.
         */
        // Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
