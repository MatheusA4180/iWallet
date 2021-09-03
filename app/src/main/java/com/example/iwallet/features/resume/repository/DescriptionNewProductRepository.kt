package com.example.iwallet.features.resume.repository

import com.example.iwallet.utils.data.local.database.ProductDAO
import com.example.iwallet.utils.model.resume.Product
import com.example.iwallet.utils.model.resume.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DescriptionNewProductRepository(
    private val productDAO: ProductDAO
) {

    suspend fun applyRegisterProductInDatabase(product: Product) {
        withContext(Dispatchers.IO){
            productDAO.saveProduct(
                ProductEntity(
                    product.broker,
                    product.name,
                    product.category,
                    product.price,
                    product.quantity,
                    product.date,
                    product.rate
                )
            )
        }
    }

}