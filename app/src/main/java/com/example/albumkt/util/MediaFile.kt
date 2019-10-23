package com.example.albumkt.util

class MediaFile {
     companion object{
        val TYPE_IMAGE = 1
        val TYPE_VIDEO = 0
    }

    var id: Long = 0

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
    }
}