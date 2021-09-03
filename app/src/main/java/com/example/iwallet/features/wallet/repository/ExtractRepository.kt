package com.example.iwallet.features.wallet.repository

import com.example.iwallet.utils.data.local.database.ExtractDAO
import com.example.iwallet.utils.data.local.database.ProductDAO
import com.example.iwallet.utils.model.wallet.Extract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExtractRepository(
    private val extractDAO: ExtractDAO
) {

    suspend fun returnListExtracts(): List<Extract> {
        return withContext(Dispatchers.IO){
            extractDAO.returnListExtract()
        }
    }

}