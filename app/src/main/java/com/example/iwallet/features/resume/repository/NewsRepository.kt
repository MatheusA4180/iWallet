package com.example.iwallet.features.resume.repository

import com.example.iwallet.utils.data.remote.ApiService
import com.example.iwallet.utils.model.resume.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(
    private val apiService: ApiService
) {

    suspend fun serchNews(theme: String): News {

        val response = apiService.serchNews(theme, API_KEY)

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception("Erro no sistema.")
        }

    }

    companion object{
        const val API_KEY = "cb5bc5c1a53d4b89a6486fe96155a744"
    }

}