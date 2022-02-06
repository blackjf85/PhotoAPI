package com.example.photoapi.model.network

import com.example.photoapi.model.network.models.Photo
import retrofit2.Response
import retrofit2.http.GET

interface PhotoService {

    @GET("photos")
    suspend fun getPhotos(): Response<List<Photo>>

}