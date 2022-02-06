package com.example.photoapi.model.repository

import com.example.photoapi.model.network.ApiManager
import com.example.photoapi.model.network.models.Photo

class PhotoRepository(
    private val apiManager: ApiManager
) {
    suspend fun getPhotos(): List<Photo>? {
        return try {
            val response = apiManager.getPhotos()
            if (response.isSuccessful) {
                response.body()
            } else {
                emptyList()
            }
        } catch (ex: Exception) {
            emptyList()
        }
    }
}