package com.example.albumkt.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.albumkt.R
import com.example.albumkt.base.BaseFragment
import com.example.albumkt.util.Constants
import com.example.albumkt.util.MediaFile
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource.Factory
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import java.io.File


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VideoPlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VideoPlayerFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var playerView: PlayerView

    private lateinit var player:SimpleExoPlayer

    override fun permissionsNeeded(): Array<String> {
        return arrayOf()
    }

    override fun init() {
        //0. Data
        val mediaFile = arguments?.getParcelable<MediaFile>(Constants.MEDIA_FILE)

        //1. init
        player = ExoPlayerFactory.newSimpleInstance(context)
        playerView.player = player

        //2. prepare the player
        // Produces DataSource instances through which media data is loaded.
        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, context?.applicationInfo?.name)
        )

        // This is the MediaSource representing the media to be played.
        // This is the MediaSource representing the media to be played.
        val videoSource: MediaSource = Factory(dataSourceFactory)
            .createMediaSource(Uri.fromFile(File(mediaFile?.path)))
        // Prepare the player with the source.
        // Prepare the player with the source.
        player.prepare(videoSource)
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
        val rootView = inflater.inflate(R.layout.fragment_video_player, container, false)
        playerView = rootView.findViewById(R.id.playerView)
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VideoPlayerFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideoPlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
