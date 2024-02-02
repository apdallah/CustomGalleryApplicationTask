package com.apdallahyousry.customgalleryapplication.data.repo

import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
import com.apdallahyousry.customgalleryapplication.data.models.AlbumPhotoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocaleAlbumRepository @Inject constructor(private val fileDataSource: FileDataSource):
    AlbumsRepository {
    override suspend fun loadAlbums(): Flow<ArrayList<AlbumModel>> {
        return flow {
            emit(fileDataSource.readAlbums())
        }
    }

    override suspend fun loadAllAlbumPhotos(): ArrayList<AlbumPhotoModel> {
         return arrayListOf()
    }
}