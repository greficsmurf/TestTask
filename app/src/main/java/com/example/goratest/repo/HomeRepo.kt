package com.example.goratest.repo

import com.example.goratest.api.ApiService
import com.example.goratest.api.models.ApiUser
import com.example.goratest.domain.User
import com.example.goratest.domain.mappers.toDomainModel
import com.example.goratest.vo.NetworkResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepo @Inject constructor(
        private val apiService: ApiService
) {

    fun getUsersResource() = object : NetworkResource<List<ApiUser>, List<User>>(){
        override suspend fun fetch(): List<ApiUser> = apiService.getUsers()

        override fun toDomainModel(data: List<ApiUser>): List<User> = data.map { it.toDomainModel() }
    }

}