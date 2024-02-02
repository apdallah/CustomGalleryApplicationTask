package com.apdallahyousry.customgalleryapplication.data.repo

import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
import com.apdallahyousry.customgalleryapplication.data.models.AlbumPhotoModel
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
   suspend fun loadAlbums(): Flow<ArrayList<AlbumModel>>
    suspend fun loadAllAlbumPhotos():ArrayList<AlbumPhotoModel>
}