package com.apdallahyousry.customgalleryapplication.data.repo

import com.apdallahyousry.customgalleryapplication.data.models.MediaItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocaleAlbumRepository @Inject constructor(private val fileDataSource: FileDataSource):
    MediaRepository {
    override suspend fun readMedia(): Flow<List<MediaItemModel>> {
        return flow {
                emit(fileDataSource.readLocalMedia())
        }
    }


}