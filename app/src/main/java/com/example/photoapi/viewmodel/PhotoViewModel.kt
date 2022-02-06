package com.example.photoapi.viewmodel

import androidx.lifecycle.*
import com.example.photoapi.model.network.models.Photo
import com.example.photoapi.model.repository.PhotoRepository
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class PhotoViewModel(
    private val photoRepository: PhotoRepository
):ViewModel() {

    private var _photos: MutableLiveData<List<Photo>?> = MutableLiveData()
    val photos: LiveData<List<Photo>?> get() = _photos

    private var _text: MutableLiveData<String?> = MutableLiveData()
    val text: LiveData<String?> get() = _text

    init {
        getPhotos()
    }

    fun setText(text: String){
        _text.value = text
    }

    private fun getPhotos(){
        viewModelScope.launch {
            val response = photoRepository.getPhotos()
            _photos.postValue(response)
        }
    }

    class Factory(
        private val photoRepository: PhotoRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PhotoViewModel::class.java)) {
                return PhotoViewModel(photoRepository) as T
            } else {
                throw RuntimeException("Could not create instance of UsersViewModel")
            }
        }
    }
}