package com.apdallahyousry.customgalleryapplication.di

import android.content.Context
import com.apdallahyousry.customgalleryapplication.data.local.ImageQueryHelper
import com.apdallahyousry.customgalleryapplication.data.local.VideoQueryHelper
import com.apdallahyousry.customgalleryapplication.data.repo.MediaRepository
import com.apdallahyousry.customgalleryapplication.data.repo.LocaleAlbumRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModules {

    @Binds
    fun provideLocaleRepository(localeAlbumRepository: LocaleAlbumRepository):MediaRepository
}