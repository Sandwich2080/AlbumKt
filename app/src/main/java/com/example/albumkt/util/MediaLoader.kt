package com.example.albumkt.util

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader

class MediaLoader {

    private lateinit var imageList: ArrayList<MediaFile>

    private lateinit var videoList: ArrayList<MediaFile>

    lateinit var imageLoadListener: ImageLoadListener

    lateinit var videoLoadListener: VideoLoadListener

    private lateinit var imagesLoader: CursorLoader

    private lateinit var videosLoader: CursorLoader

    /**
     *
     */
    fun loadImages(c: Context) {

        imagesLoader = CursorLoader(
            c, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
            arrayOf("image/jpeg", "image/png"),
            MediaStore.Images.Media.DATE_TAKEN + " desc"
        )

        // register imageLoadListener
        imagesLoader.registerListener(
            imagesLoader.id, Loader.OnLoadCompleteListener(
                fun(loader: Loader<Cursor>, cursor: Cursor?) {

                    if (cursor != null && cursor.moveToFirst()) {
                        imageList = ArrayList()
                        do {
                            val id =
                                cursor.getLong(cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID))
                            val path =
                                cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
                            val dateModified =
                                cursor.getLong(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_MODIFIED))
                            val dateAdded =
                                cursor.getLong(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_ADDED))
                            val dateTaken =
                                cursor.getLong(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN))

                            val mediaFile =
                                MediaFile(
                                    id,
                                    path,
                                    null,
                                    MediaFile.TYPE_IMAGE,
                                    dateModified,
                                    dateAdded,
                                    dateTaken
                                )

                            imageList.add(mediaFile)

                        } while (cursor.moveToNext())

                        cursor.close()
                    }

                    imageLoadListener.onLoadComplete(imageList)

                }

            ))
        // register cancelling imageLoadListener
        imagesLoader.registerOnLoadCanceledListener(Loader.OnLoadCanceledListener {

            fun(loader: Loader<Cursor>) {
                imageLoadListener.onCancel()
            }
        })

        // start to load
        imagesLoader.startLoading()
    }

    fun loadVideos(c: Context) {

        videosLoader = CursorLoader(
            c,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            null,
            MediaStore.Video.Media.MIME_TYPE + "=? ",
            arrayOf("video/mp4"),
            MediaStore.Video.VideoColumns.DATE_TAKEN + " desc"
        )

        videosLoader.registerListener(videosLoader.id, Loader.OnLoadCompleteListener(

            fun(loader: Loader<Cursor>, cursor: Cursor?) {
                if (cursor != null && cursor.moveToNext()) {
                    videoList = ArrayList()
                    do {
                        val id =
                            cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID))
                        val path =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA))
                        val dateModified =
                            cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATE_MODIFIED))
                        val dateAdded =
                            cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATE_ADDED))
                        val dateTaken =
                            cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATE_TAKEN))

                        val videoFile =
                            MediaFile(
                                id,
                                path,
                                null,
                                MediaFile.TYPE_VIDEO,
                                dateModified,
                                dateAdded,
                                dateTaken
                            )
                        videoList.add(videoFile)
                    } while (cursor.moveToNext())
                    cursor.close()

                    videoLoadListener.onLoadComplete(videoList)
                }
            }
        ))

        videosLoader.registerOnLoadCanceledListener(
            Loader.OnLoadCanceledListener {
                fun(loader: Loader<Cursor>) {
                    videoLoadListener.onCancel()

                }

            }
        )

        videosLoader.startLoading()


    }

    fun cancel() {
        imagesLoader.cancelLoadInBackground()
    }

    interface ImageLoadListener {
        fun onLoadComplete(fileList: ArrayList<MediaFile>?)
        fun onCancel()
    }

    interface VideoLoadListener : ImageLoadListener
}