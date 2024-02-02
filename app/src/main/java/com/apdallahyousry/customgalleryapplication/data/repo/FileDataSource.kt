package com.apdallahyousry.customgalleryapplication.data.repo

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import com.apdallahyousry.customgalleryapplication.data.models.MediaItemModel
import com.apdallahyousry.customgalleryapplication.data.models.MediaType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FileDataSource @Inject constructor(@ApplicationContext private val appContext: Context) {

    fun readLocalMedia(): List<MediaItemModel> {
        return readImagesMedia()+readVideosMedia()
    }

    private fun readImagesMedia(): ArrayList<MediaItemModel> {
        val result = arrayListOf<MediaItemModel>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA,
        )

        val mediaQuery = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val cur: Cursor? = appContext.contentResolver.query(
            mediaQuery,
            projection,  // Which columns to return
            null,  // Which rows to return (all rows)
            null,  // Selection arguments (none)
            null // Ordering
        )


        Log.i("ListingImages", " query count=" + cur?.count)
        cur?.let {
            if (it.moveToFirst()) {

                val albumNameColumn =
                    it.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                val displayNameColumn = it.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
                val pathColumn = it.getColumnIndex(MediaStore.Images.Media.DATA)
                val idColumn = it.getColumnIndex(MediaStore.Images.Media._ID)
                do {

                    val albumName = it.getString(albumNameColumn)
                    val displayName = it.getString(displayNameColumn)
                    val path = it.getString(pathColumn)
                    val id = it.getInt(idColumn)

                    result.add(
                        MediaItemModel(
                            id = id,
                            title = displayName,
                            albumName = albumName,
                            mediaPath = path,
                            mediaType = MediaType.MEDIA_TYPE_IMAGE
                        )
                    )
                } while (it.moveToNext())
            }
        }
        cur?.close()

        return result
    }
    private fun readVideosMedia(): ArrayList<MediaItemModel>{
        val result = arrayListOf<MediaItemModel>()
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATA,
        )

        val mediaQuery = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val cur: Cursor? = appContext.contentResolver.query(
            mediaQuery,
            projection,  // Which columns to return
            null,  // Which rows to return (all rows)
            null,  // Selection arguments (none)
            null // Ordering
        )


        Log.i("ListingImages", " query count=" + cur?.count)
        cur?.let {
            if (it.moveToFirst()) {

                val albumNameColumn =
                    it.getColumnIndex(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)
                val displayNameColumn = it.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME)
                val pathColumn = it.getColumnIndex(MediaStore.Video.Media.DATA)
                val idColumn = it.getColumnIndex(MediaStore.Video.Media._ID)
                do {

                    val albumName = it.getString(albumNameColumn)
                    val displayName = it.getString(displayNameColumn)
                    val path = it.getString(pathColumn)
                    val id = it.getInt(idColumn)

                    result.add(
                        MediaItemModel(
                            id = id,
                            title = displayName,
                            albumName = albumName,
                            mediaPath = path,
                            mediaType = MediaType.MEDIA_TYPE_VIDEO
                        )
                    )
                } while (it.moveToNext())
            }
        }
        cur?.close()

        return result
    }
}