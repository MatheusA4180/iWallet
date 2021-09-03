package com.example.iwallet.utils.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.iwallet.utils.model.resume.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {

    abstract fun productDAO(): ProductDAO

    companion object {

        private const val NAME_DB = "ProductCache"

        fun getInstance(context: Context?): AppDatabase {
            return Room
                .databaseBuilder(context!!, AppDatabase::class.java, NAME_DB)
                .build()
        }

    }

}