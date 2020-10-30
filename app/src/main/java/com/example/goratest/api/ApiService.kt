package com.example.goratest.api

import com.example.goratest.api.models.ApiAlbum
import com.example.goratest.api.models.ApiPhoto
import com.example.goratest.api.models.ApiUser
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<ApiUser>

    @GET("users/{userId}/albums")
    suspend fun getUserAlbums(@Path("userId") userId: Long): List<ApiAlbum>

    @GET("albums/{albumId}/photos")
    suspend fun getAlbumPhotos(@Path("albumId") albumId: Long): List<ApiPhoto>
}