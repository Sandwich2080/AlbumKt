package com.example.albumkt.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.albumkt.R
import com.example.albumkt.base.BaseFragment
import com.example.albumkt.ui.activity.VideoPlayActivity
import com.example.albumkt.util.Constants
import com.example.albumkt.util.MediaFile
import com.example.albumkt.util.SettingsConfig
import com.github.chrisbanes.photoview.PhotoView
import java.io.File


/**
 * A simple [Fragment] subclass.
 * Use the [PreviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreviewFragment : BaseFragment() {
    private lateinit var file: MediaFile

    private lateinit var photoView: PhotoView

    private lateinit var ivPlay: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            file = it.getParcelable(Constants.MEDIA_FILE)!!
        }
    }

    override fun init() {
    }

    override fun permissionsNeeded(): Array<String> {
        return arrayOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val contentView = inflater.inflate(R.layout.fragment_preview, container, false)
        photoView = contentView.findViewById(R.id.pv_image)
        ivPlay = contentView.findViewById(R.id.iv_play)
        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoView.let {
            Glide.with(view.context).load(Uri.fromFile(File(file.path))).centerInside().into(it)
        }

        if (file.type == MediaFile.TYPE_VIDEO) {

            photoView.isZoomable=false

            ivPlay.visibility = View.VISIBLE
            ivPlay.setOnClickListener {
                playVideo()
            }
        } else {
            ivPlay.visibility = View.GONE
        }

        photoView.setOnClickListener {
            //(activity as PreviewActivity).switchScreen()
        }

    }

    private fun playVideo() {
        if (SettingsConfig.ins.isInternalPlayer()){
            playWithInternalPlayer()
            return
        }
        try {
            val it = Intent(Intent.ACTION_VIEW)
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            it.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            val uri: Uri?
            uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                context?.let { it1 ->
                    FileProvider.getUriForFile(
                        it1,
                        it1.packageName + ".fileprovider",
                        File(file.path)
                    )
                }
            } else {
                Uri.fromFile(File(file.path))
            }

            it.setDataAndType(uri, "video/*")
            activity?.startActivity(it)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun playWithInternalPlayer(){
        val it = Intent(activity,VideoPlayActivity::class.java)
        it.putExtra(Constants.MEDIA_FILE, file)
        activity?.startActivity(it)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param file Parameter 1.
         * @return A new instance of fragment PreviewFragment.
         */
        @JvmStatic
        fun newInstance(file: MediaFile) =
            PreviewFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.MEDIA_FILE, file)
                }
            }
    }
}