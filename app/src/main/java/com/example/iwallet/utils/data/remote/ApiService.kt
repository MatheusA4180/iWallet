package com.example.iwallet.utils.data.remote

import com.example.iwallet.utils.model.resume.News
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun serchNews(
        @Query("q") theme: String,
        @Query("apiKey") apiKey: String
    ): Response<News>

    companion object {
        fun getEndPointInstance(): ApiService {
            return Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
        }
    }

}