package com.apdallahyousry.customgalleryapplication.data.repo

 import com.apdallahyousry.customgalleryapplication.data.local.ImageQueryHelper
 import com.apdallahyousry.customgalleryapplication.data.local.MediaQueryHelper
 import com.apdallahyousry.customgalleryapplication.data.local.VideoQueryHelper
 import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
 import com.apdallahyousry.customgalleryapplication.data.models.MediaItemModel
 import com.apdallahyousry.customgalleryapplication.data.models.MediaMapper
 import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
 import kotlinx.coroutines.flow.map
 import kotlinx.coroutines.flow.zip
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocaleAlbumRepository @Inject constructor(
    private val imageQueryHelper: ImageQueryHelper ,
    private val videoQueryHelper: VideoQueryHelper,
    private val mapper: MediaMapper
) :
    MediaRepository {
    override suspend fun readMedia(): Flow<List<AlbumModel>> {
        return imageQueryHelper.queryMedia().zip(videoQueryHelper.queryMedia())
        { images, videos ->
                        return@zip images + videos
        }.map { mapper.fromMediaItemsToAlbums(it) }
    }


}