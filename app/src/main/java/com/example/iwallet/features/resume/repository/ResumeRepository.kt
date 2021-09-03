package com.example.iwallet.features.resume.repository

import com.example.iwallet.utils.data.local.database.ProductDAO
import com.example.iwallet.utils.model.resume.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResumeRepository(
    private val productDAO: ProductDAO
) {

    suspend fun returnListProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            productDAO.returnListProduct()
        }
    }

}