package com.example.albumkt.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.albumkt.R
import com.example.albumkt.util.MediaFile

class FileAdapter : BaseAdapter {

    class ViewHolder {
        lateinit var imageView: ImageView
        lateinit var ivPlay: ImageView
    }

    var act: Activity

    var fileList: ArrayList<MediaFile>

    var inflater: LayoutInflater

    constructor(act: Activity, fileList: ArrayList<MediaFile>) {
        this.act = act
        this.fileList = fileList
        this.inflater = LayoutInflater.from(this.act)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var viewHolder: ViewHolder
        var retView: View

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

        when (fileList[position].type) {
            MediaFile.TYPE_VIDEO -> {
                viewHolder.ivPlay.visibility = View.VISIBLE
            }
            else -> {
                viewHolder.ivPlay.visibility = View.GONE
            }
        }

        Glide.with(act).load(fileList[position].path).centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true)
            .into(viewHolder.imageView)

        return retView

    }

    override fun getItem(position: Int): Any {
        return fileList?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return fileList?.get(position)?.id
    }

    override fun getCount(): Int {
        return fileList?.size
    }
}