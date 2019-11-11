package com.example.albumkt.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaFile(var id: Long, var path: String?, var thumbnailPath: String?, var type: Int, var dateModified: Long) : Parcelable {


    /*override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeLong(id)
        out.writeString(path)
        out.writeString(thumbnailPath)
        out.writeInt(type)
        out.writeLong(dateModified)
    }

    @JvmField
    val CREATOR: Creator<MediaFile?> = object : Creator<MediaFile?> {
        override fun createFromParcel(`in`: Parcel): MediaFile? {
            return MediaFile(`in`)
        }

        override fun newArray(size: Int): Array<MediaFile?> {
            return arrayOfNulls<MediaFile>(size)
        }
    }

    private fun MediaFile(`in`: Parcel): MediaFile {
        id = `in`.readLong()
        path = `in`.readString()
        thumbnailPath = `in`.readString()
        type = `in`.readInt()
        dateModified = `in`.readLong()
        return MediaFile(id, path, thumbnailPath, type, dateModified)
    }*/


    companion object {
        const val TYPE_IMAGE = 1
        const val TYPE_VIDEO = 0
    }

    /*var id: Long = 0

    var path: String? = null

    var thumbnailPath: String? = null

    var type: Int = 0

    var dateModified: Long = 0

    constructor(id: Long, path: String?, thumbnailPath: String?, type: Int, dateModified: Long) {
        this.id = id
        this.path = path
        this.thumbnailPath = thumbnailPath
        this.type = type
        this.dateModified = dateModified
    }*/
}