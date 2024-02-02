package com.apdallahyousry.customgalleryapplication.data.models

import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton


class MediaMapper @Inject constructor() {
    fun fromMediaItemsToAlbums(mediaItems:List<MediaItemModel>):List<AlbumModel>{
        val allImages=mediaItems.filter { it.mediaType==MediaType.MEDIA_TYPE_IMAGE }
        val allImagesAlbum= AlbumModel(mediaItems = allImages, title = "All Images", thumbnail = allImages.firstOrNull()?.mediaPath)
        val videos=mediaItems.filter { it.mediaType==MediaType.MEDIA_TYPE_VIDEO }
        val allVideosAlbum= AlbumModel(mediaItems = videos, title = "All Videos", thumbnail = videos.firstOrNull()?.mediaPath)

        return listOf(allImagesAlbum,allVideosAlbum)+ mediaItems.groupBy {
            it.albumName
        }.map {
            AlbumModel(mediaItems = it.value, title = it.key, thumbnail = it.value.firstOrNull()?.mediaPath)
        }.sortedBy { it.title }
    }
}