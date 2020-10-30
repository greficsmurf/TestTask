package com.example.goratest.repo

import com.example.goratest.api.ApiService
import com.example.goratest.api.models.ApiPhoto
import com.example.goratest.domain.Photo
import com.example.goratest.domain.mappers.toDomainModel
import com.example.goratest.vo.NetworkResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRepo @Inject constructor(
    private val apiService: ApiService
){

    fun getPhotosResource(albumId: Long) = object : NetworkResource<List<ApiPhoto>, List<Photo>>(){
        override suspend fun fetch(): List<ApiPhoto> = apiService.getAlbumPhotos(albumId)

        override fun toDomainModel(data: List<ApiPhoto>): List<Photo> = data.map { it.toDomainModel() }
    }.asLiveData()

}