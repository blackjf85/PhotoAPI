package com.example.photoapi.model.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Photo(
    var albumId: Int,
    var id: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String
)
