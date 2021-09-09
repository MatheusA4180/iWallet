package com.example.iwallet.features.resume.repository

import com.example.iwallet.utils.data.local.SessionManager
import com.example.iwallet.utils.data.local.database.NewsDAO
import com.example.iwallet.utils.data.remote.ApiService
import com.example.iwallet.utils.model.resume.News
import com.example.iwallet.utils.model.resume.NewsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(
    private val sessionManager: SessionManager,
    private val apiService: ApiService,
    private val newsDAO: NewsDAO
) {

    fun saveCacheNews(save: Boolean) = sessionManager.saveCacheNews(save)

    fun savedCacheNews(): Boolean = sessionManager.savedCacheNews()

    suspend fun serchNews(): List<NewsEntity> {

        val response = apiService.serchNews(sessionManager.getSaveThemeNews(), API_KEY)

        if (response.isSuccessful) {
            saveCache(mapNewsForNewsEntity(response.body()!!))
            return returnListNewsCache()
        } else {
            throw Exception("Erro ao carregar as notícias.")
        }

    }

    private fun mapNewsForNewsEntity(response: News): List<NewsEntity> {
        return response.articles.map {
            NewsEntity(it.title,it.description,it.url,it.urlToImage)
        }
    }

    suspend fun returnListNewsCache(): List<NewsEntity> {
        return withContext(Dispatchers.IO) {
            newsDAO.returnListNews()
        }
    }

    suspend fun saveCache(response: List<NewsEntity>) {
        withContext(Dispatchers.IO) {
            newsDAO.deleteAllNews()
            newsDAO.saveNews(response)
        }
    }

    companion object {
        const val API_KEY = "cb5bc5c1a53d4b89a6486fe96155a744"
    }

}
