package com.example.iwallet.utils.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iwallet.utils.model.wallet.Extract
import com.example.iwallet.utils.model.wallet.ExtractEntity

@Dao
interface ExtractDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveExtract(extractEntity: ExtractEntity)

    @Query("SELECT * FROM ExtractEntity")
    fun returnListExtract(): List<Extract>

}