package com.apdallahyousry.customgalleryapplication.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
import com.apdallahyousry.customgalleryapplication.data.models.MediaMapper
import com.apdallahyousry.customgalleryapplication.data.repo.MediaRepository
import com.apdallahyousry.customgalleryapplication.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val repository: MediaRepository) : ViewModel() {
    private val _allAlbums: MutableLiveData<List<AlbumModel>> = MutableLiveData()
    val albumsLiveData: LiveData<List<AlbumModel>> = _allAlbums

    val selectedAlbum:SingleLiveEvent<AlbumModel> = SingleLiveEvent()
    val error:SingleLiveEvent<String> = SingleLiveEvent()
    val isLoading:SingleLiveEvent<Boolean> = SingleLiveEvent()


    fun loadAlbums() {
        viewModelScope.launch {
            repository.readMedia().flowOn(Dispatchers.IO)
                .onStart {
                    isLoading.postValue(true)
                }.catch {
                    error.postValue(it.message)
                    isLoading.postValue(false)
                }.collect {
                    _allAlbums.postValue(it)
                    isLoading.postValue(false)
                }
        }


    }
    fun onAlbumSelected(albumModel: AlbumModel)=selectedAlbum.postValue(albumModel)

}