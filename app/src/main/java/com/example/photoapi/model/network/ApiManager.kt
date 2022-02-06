package com.example.photoapi.model.network

class ApiManager {

    private var photoService: PhotoService =
        RetrofitInstance.providesRetrofit().create(PhotoService::class.java)

    suspend fun getPhotos() = photoService.getPhotos()

}