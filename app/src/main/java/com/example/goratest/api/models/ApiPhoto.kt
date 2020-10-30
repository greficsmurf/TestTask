package com.example.goratest.api.models

data class ApiPhoto(
    val albumId: Long,
    val id: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)