package com.apdallahyousry.customgalleryapplication.data.repo

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FileDataSource @Inject constructor(@ApplicationContext private val appContext: Context) {
    fun readAlbums(): ArrayList<AlbumModel> {
        val result= arrayListOf<AlbumModel>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DATE_TAKEN
        )

        val images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val cur: Cursor? = appContext.contentResolver.query(
            images,
            projection,  // Which columns to return
            null,  // Which rows to return (all rows)
            null,  // Selection arguments (none)
            null // Ordering
        )

        Log.i("ListingImages", " query count=" + cur?.count)
        cur?.let {
            if (it.moveToFirst()) {

                val bucketColumn = it.getColumnIndex(
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME
                )
                val idColumn=it.getColumnIndex(MediaStore.Images.Media._ID)
                do {

                    val displayName= it.getString(bucketColumn)
                    val idColumn= it.getInt(idColumn)

                    result.add(AlbumModel(idColumn,displayName))
                } while (it.moveToNext())
            }
        }
        cur?.close()

        return result
    }
}