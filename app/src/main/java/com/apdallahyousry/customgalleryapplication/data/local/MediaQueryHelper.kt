package com.apdallahyousry.customgalleryapplication.data.local

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.apdallahyousry.customgalleryapplication.data.models.MediaItemModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

abstract class MediaQueryHelper<T>(
   protected val context: Context,
    private val mediaUri: Uri,
    private val sortOrder: String
) {

    abstract fun getColumnNames(): Array<String>

    abstract fun createMediaObject(cursor: Cursor): T

    fun queryMedia(): Flow<List<T>> {
        val projection = getColumnNames()
        val result= arrayListOf<T>()
        val cursor: Cursor? = context.contentResolver.query(
            mediaUri,
            projection,
            null,
            null,
            sortOrder
        )

        cursor?.use {
            while (it.moveToNext()) {
                result.add( createMediaObject(it))

            }
        }
        cursor?.close()
        return flowOf(result)
    }
}



// Similar class for VideoQueryHelper
