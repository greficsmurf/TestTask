package com.example.goratest.domain.mappers

import com.example.goratest.api.models.ApiUser
import com.example.goratest.domain.User

fun ApiUser.toDomainModel() = User(
        userId,
        name,
        userName,
        email
)