package com.apdallahyousry.customgalleryapplication.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
import com.apdallahyousry.customgalleryapplication.data.models.MediaMapper
import com.apdallahyousry.customgalleryapplication.data.repo.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val repository: MediaRepository, private val mapper: MediaMapper) : ViewModel() {
    private val _allAlbums: MutableLiveData<List<AlbumModel>> = MutableLiveData()
    val albumsLiveData: LiveData<List<AlbumModel>> = _allAlbums



    fun loadAlbums() {
        viewModelScope.launch {
            repository.readMedia().flowOn(Dispatchers.IO)
                .onStart {

                }.catch {

                }.map {
                    mapper.fromMediaItemsToAlbums(it)
                }.collect {
                    _allAlbums.postValue(it)
                }
        }


    }

}