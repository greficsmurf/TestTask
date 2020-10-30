package com.example.goratest.ui.albums

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.example.goratest.repo.AlbumsRepo
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(
    private val albumsRepo: AlbumsRepo
): ViewModel(){

    private val _userId = MutableLiveData<Long>()

    val albumsResource = _userId.switchMap {
        albumsRepo.getUsersResource(it).asLiveData()
    }

    val albums = albumsResource.map {
        it.data
    }
    fun setUserId(id: Long){
        _userId.value = id
    }

}