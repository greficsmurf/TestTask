package com.example.goratest.repo

import com.example.goratest.api.ApiService
import com.example.goratest.api.models.ApiAlbum
import com.example.goratest.domain.Album
import com.example.goratest.domain.mappers.toDomainModel
import com.example.goratest.vo.NetworkResource
import javax.inject.Inject

class AlbumsRepo @Inject constructor(
    val apiService: ApiService
){
    fun getUsersResource(userId: Long) = object : NetworkResource<List<ApiAlbum>, List<Album>>(){
        override suspend fun fetch(): List<ApiAlbum> = apiService.getUserAlbums(userId)

        override fun toDomainModel(data: List<ApiAlbum>): List<Album> = data.map { it.toDomainModel() }
    }

}