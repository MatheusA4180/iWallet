package com.example.iwallet.features.resume.repository

import com.example.iwallet.features.resume.fragments.DescriptionProductFragment.Companion.APPLICATION
import com.example.iwallet.utils.data.local.database.ExtractDAO
import com.example.iwallet.utils.data.local.database.ProductDAO
import com.example.iwallet.utils.model.resume.Product
import com.example.iwallet.utils.model.resume.ProductEntity
import com.example.iwallet.utils.model.wallet.ExtractEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DescriptionNewProductRepository(
    private val productDAO: ProductDAO,
    private val extractDAO: ExtractDAO
) {

    suspend fun applyRegisterProductInDatabase(product: Product) {
        saveExtract(product)
        withContext(Dispatchers.IO) {
            productDAO.saveProduct(
                ProductEntity(
                    product.broker,
                    product.name,
                    product.category,
                    product.price,
                    product.quantity,
                    product.total,
                    product.date,
                    product.rate,
                    product.color
                )
            )
            mapForPartOfWallet()
        }
    }

    private suspend fun saveExtract(productNew: Product) {
        withContext(Dispatchers.IO) {
            extractDAO.saveExtract(
                ExtractEntity(
                    productNew.broker,
                    productNew.name,
                    productNew.category,
                    productNew.total,
                    productNew.date,
                    APPLICATION
                )
            )
        }
    }

    private suspend fun mapForPartOfWallet() {
        withContext(Dispatchers.IO) {
            var total = 0.0
            val listProducts = productDAO.returnListProduct()
            listProducts.forEach { product ->
                total += product.total.toDouble()
            }
            listProducts.forEach { product ->
                updateRate(product.name, String
                    .format("%.2f", ((product.total.toDouble() / total)*100) ).toDouble() )
            }
        }
    }

    private suspend fun updateRate(nameProduct: String, rateProduct: Double) {
        withContext(Dispatchers.IO) {
            productDAO.updateRate(
                nameProduct,
                rateProduct.toString()
            )
        }
    }

}