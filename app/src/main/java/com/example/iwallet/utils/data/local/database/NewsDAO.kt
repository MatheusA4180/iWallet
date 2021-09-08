package com.example.iwallet.utils.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iwallet.utils.model.resume.NewsEntity

@Dao
interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNews(listNews: List<NewsEntity>)

    @Query("SELECT * FROM NewsEntity")
    fun returnListNews(): List<NewsEntity>

    @Query("DELETE FROM NewsEntity")
    fun deleteAllNews()

}