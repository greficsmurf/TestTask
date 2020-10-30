package com.example.goratest.domain.mappers

import com.example.goratest.api.models.ApiPhoto
import com.example.goratest.domain.Photo

fun ApiPhoto.toDomainModel() = Photo(
    albumId,
    id,
    title,
    url,
    thumbnailUrl
)