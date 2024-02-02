package com.apdallahyousry.customgalleryapplication.data.models

import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class MediaMapper @Inject constructor() {
    fun fromMediaItemsToAlbums(mediaItems:List<MediaItemModel>):List<AlbumModel>{
        return mediaItems.groupBy {
            it.albumName
        }.map {
            AlbumModel(mediaItems = it.value, title = it.key, thumbnail = it.value.firstOrNull()?.mediaPath)
        }.sortedBy { it.title }
    }
}