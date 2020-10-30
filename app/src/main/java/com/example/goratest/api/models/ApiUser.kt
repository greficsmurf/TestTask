package com.example.goratest.api.models

import com.squareup.moshi.Json

data class ApiUser(
        @Json(name = "id")
        val userId: Long,
        val name: String,
        @Json(name = "username")
        val userName: String,
        val email: String
)