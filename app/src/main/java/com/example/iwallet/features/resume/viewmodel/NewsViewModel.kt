package com.example.iwallet.features.resume.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.resume.repository.NewsRepository
import com.example.iwallet.utils.model.resume.News
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository
):ViewModel() {

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    private val _responseErro = MutableLiveData<String>()
    val responseErro: LiveData<String> = _responseErro

    private val _listNews = MutableLiveData<News>()
    val listNews: LiveData<News> = _listNews

    fun requestNews(){
        viewModelScope.launch {
            _showLoading.postValue(true)
            try {
                val response = newsRepository.serchNews("criptomoedas")
                _listNews.postValue(response)
            } catch (e: Exception) {
                _responseErro.postValue(e.message)
            }
            _showLoading.postValue(false)
        }
    }

}