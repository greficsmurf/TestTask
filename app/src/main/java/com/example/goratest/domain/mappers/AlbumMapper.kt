package com.example.goratest.domain.mappers

import com.example.goratest.api.models.ApiAlbum
import com.example.goratest.domain.Album

fun ApiAlbum.toDomainModel() = Album(
    userId,
    id,
    title
)