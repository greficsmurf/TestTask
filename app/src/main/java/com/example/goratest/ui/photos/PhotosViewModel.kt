package com.example.goratest.ui.photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.example.goratest.repo.PhotosRepo
import javax.inject.Inject

class PhotosViewModel @Inject constructor(
    private val photoRepo: PhotosRepo
) : ViewModel() {

    private val _albumId = MutableLiveData<Long>()

    val photosResource = _albumId.switchMap {
        photoRepo.getPhotosResource(it)
    }

    val photos = photosResource.map {
        it.data
    }

    fun setAlbumId(id: Long){
        _albumId.value = id
    }
}