package com.apdallahyousry.customgalleryapplication.di

import com.apdallahyousry.customgalleryapplication.data.repo.MediaRepository
import com.apdallahyousry.customgalleryapplication.data.repo.LocaleAlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModules {
    @Binds
    fun provideMainRepositoryImpl(repository: LocaleAlbumRepository): MediaRepository
}