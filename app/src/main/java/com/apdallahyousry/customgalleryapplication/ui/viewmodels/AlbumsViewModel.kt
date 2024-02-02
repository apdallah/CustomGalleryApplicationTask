package com.apdallahyousry.customgalleryapplication.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
import com.apdallahyousry.customgalleryapplication.data.repo.AlbumsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val repository: AlbumsRepository) : ViewModel() {
    private val _allAlbums: MutableLiveData<ArrayList<AlbumModel>> = MutableLiveData()
    val albumsLiveData: LiveData<ArrayList<AlbumModel>> = _allAlbums

    fun loadAlbums() {
        viewModelScope.launch {
            repository.loadAlbums().flowOn(Dispatchers.IO)
                .onStart {

                }.catch {

                }.collect {
                    _allAlbums.postValue(it)
                }
        }


    }

}