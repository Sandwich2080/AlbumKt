package com.example.albumkt.ui.adapter

import android.app.Activity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.albumkt.R
import com.example.albumkt.util.LogUtils
import com.example.albumkt.util.MediaFile

class FileAdapter(var act: Activity, var fileList: ArrayList<MediaFile>) : BaseAdapter() {

    class ViewHolder {
        lateinit var imageView: ImageView
        lateinit var ivPlay: ImageView
    }

    private var inflater: LayoutInflater = LayoutInflater.from(this.act)

    /**
     * Width of items in the GridViews
     */
    private val itemWidth = displayMetrics().widthPixels/4

    private fun displayMetrics():DisplayMetrics {
        LogUtils.debug("Getting DisplayMetrics")
        return this.act.resources.displayMetrics
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val viewHolder: ViewHolder
        val retView: View

        if (convertView == null) {
            retView = inflater.inflate(R.layout.layout_image_item, null, false)

            viewHolder = ViewHolder()
            viewHolder.imageView = retView.findViewById(R.id.iv_photo)
            viewHolder.ivPlay = retView.findViewById(R.id.iv_play)

            retView.tag = viewHolder

        } else {
            retView = convertView
            viewHolder = retView.tag as ViewHolder
        }
        // set item width and height
        val params = ViewGroup.LayoutParams(itemWidth,itemWidth)
        retView.layoutParams = params

        when (fileList[position].type) {
            MediaFile.TYPE_VIDEO -> {
                viewHolder.ivPlay.visibility = View.VISIBLE
            }
            else -> {
                viewHolder.ivPlay.visibility = View.GONE
            }
        }

        Glide.with(act).load(fileList[position].path).centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).skipMemoryCache(true)
            .into(viewHolder.imageView)

        return retView

    }

    override fun getItem(position: Int): Any {
        return fileList[position]
    }

    override fun getItemId(position: Int): Long {
        return fileList[position].id
    }

    override fun getCount(): Int {
        return fileList.size
    }
}