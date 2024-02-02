package com.apdallahyousry.customgalleryapplication.data.repo

import com.apdallahyousry.customgalleryapplication.data.models.MediaItemModel
import kotlinx.coroutines.flow.Flow

interface MediaRepository {
   suspend fun readMedia(): Flow<List<MediaItemModel>>
 }