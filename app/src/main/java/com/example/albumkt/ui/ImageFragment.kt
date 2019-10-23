package com.example.albumkt.ui

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.albumkt.R
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
class ImageFragment : Fragment() {

    private val TAG:String = ImageFragment.javaClass.simpleName
    // Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var gridView: GridView

    private lateinit var fileAdapter: FileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
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
        gridView = inflater.inflate(R.layout.fragment_image, container, false) as GridView
        return gridView
    }

    class FileAdapter:BaseAdapter{

        class ViewHolder{
            lateinit var imageView: ImageView
        }

        lateinit var act: Activity

        lateinit var imageList: ArrayList<MediaFile>

        lateinit var inflater: LayoutInflater

        constructor() : super()

        constructor(act:Activity,imageList: ArrayList<MediaFile>){
            this.act = act
            this.imageList = imageList
            this.inflater = LayoutInflater.from(this.act)
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var viewHolder:ViewHolder
            var retView:View

            if (convertView==null){
                retView = inflater.inflate(R.layout.layout_image_item,null,false) as ImageView

                viewHolder = ViewHolder()
                viewHolder.imageView = retView

                retView.tag = viewHolder

            }else{
                retView = convertView as ImageView
                viewHolder = retView.tag as ViewHolder
            }

            Glide.with(act).load(imageList.get(position).path).into(viewHolder.imageView)


            return retView

        }

        override fun getItem(position: Int): Any {
            return imageList?.get(position)
        }

        override fun getItemId(position: Int): Long {
            return imageList?.get(position)?.id
        }

        override fun getCount(): Int {
            return imageList?.size
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val loader = MediaLoader()
        loader.imageLoadListener = object :MediaLoader.ImageLoadListener{
            override fun onLoadComplete(fileList: ArrayList<MediaFile>?){
                if (fileList!=null){
                    fileAdapter = FileAdapter(activity as Activity,fileList)
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        /*if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
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
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
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
