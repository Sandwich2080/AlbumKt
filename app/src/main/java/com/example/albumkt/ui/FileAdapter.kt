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

class FileAdapter:BaseAdapter{

    class ViewHolder{
        lateinit var imageView: ImageView
    }

    var act: Activity

    var imageList: ArrayList<MediaFile>

    var inflater: LayoutInflater

    constructor(act: Activity, imageList: ArrayList<MediaFile>){
        this.act = act
        this.imageList = imageList
        this.inflater = LayoutInflater.from(this.act)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var viewHolder:ViewHolder
        var retView: View

        if (convertView==null){
            retView = inflater.inflate(R.layout.layout_image_item,null,false) as ImageView

            viewHolder = ViewHolder()
            viewHolder.imageView = retView

            retView.tag = viewHolder

        }else{
            retView = convertView as ImageView
            viewHolder = retView.tag as ViewHolder
        }

        Glide.with(act).load(imageList.get(position).path).diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(true).into(viewHolder.imageView)

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