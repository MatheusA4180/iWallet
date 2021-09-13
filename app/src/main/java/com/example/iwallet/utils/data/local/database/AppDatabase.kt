package com.example.iwallet.utils.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.iwallet.utils.model.resume.NewsEntity
import com.example.iwallet.utils.model.resume.ProductEntity
import com.example.iwallet.utils.model.wallet.ExtractEntity

@Database(
    entities = [ProductEntity::class, ExtractEntity::class,NewsEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun productDAO(): ProductDAO

    abstract fun extractDAO(): ExtractDAO

    abstract fun newsDAO(): NewsDAO

    abstract fun financesDAO(): FinancesDAO

    companion object {

        private const val NAME_DB = "Database App"

        fun getInstance(context: Context?): AppDatabase {
            return Room
                .databaseBuilder(context!!, AppDatabase::class.java, NAME_DB)
                .build()
        }

    }

}
