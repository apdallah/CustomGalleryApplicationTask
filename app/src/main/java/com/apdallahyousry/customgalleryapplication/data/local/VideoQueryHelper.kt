package com.apdallahyousry.customgalleryapplication.data.local

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.apdallahyousry.customgalleryapplication.data.models.MediaItemModel
import com.apdallahyousry.customgalleryapplication.data.models.MediaType
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class VideoQueryHelper @Inject constructor(@ApplicationContext context: Context) :
    MediaQueryHelper<MediaItemModel>(context, MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "${MediaStore.Images.Media.DATE_TAKEN} DESC") {
    override fun getColumnNames(): Array<String> {
        return arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATE_TAKEN,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DATA,
        )
    }

    override fun createMediaObject(cursor: Cursor): MediaItemModel {
        val id: Long = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID))
        val imagePath: String = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))
        val title: String = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME))
        val albumTitle: String = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME))

        return MediaItemModel(id=id, title = title, mediaPath = imagePath, albumName = albumTitle, mediaType = MediaType.MEDIA_TYPE_IMAGE)
    }
}