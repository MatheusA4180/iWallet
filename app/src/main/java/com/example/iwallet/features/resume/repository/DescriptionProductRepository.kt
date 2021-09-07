package com.example.iwallet.features.resume.repository

import com.example.iwallet.features.resume.fragments.DescriptionProductFragment.Companion.APPLICATION
import com.example.iwallet.utils.data.local.database.ExtractDAO
import com.example.iwallet.utils.data.local.database.ProductDAO
import com.example.iwallet.utils.model.resume.Product
import com.example.iwallet.utils.model.wallet.ExtractEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DescriptionProductRepository(
    private val productDAO: ProductDAO,
    private val extractDAO: ExtractDAO
) {

    suspend fun registerUpdateProduct(productNew: Product, buttonPressed: String) {
        withContext(Dispatchers.IO) {
            val productOld = productDAO.searchProduct(productNew.name)
            if (buttonPressed == APPLICATION) {
                saveExtract(productNew, buttonPressed)
                productDAO.updateProduct(
                    productOld.name,
                    calcNewPriceApplication(productOld, productNew),
                    calcNewQuantityApplication(productOld, productNew),
                    calcNewTotalApplication(productOld, productNew),
                    productNew.color
                )
                mapForPartOfWallet()
            } else {
                saveExtract(productNew, buttonPressed)
                productDAO.updateProduct(
                    productOld.name,
                    calcNewPriceRescue(productOld, productNew),
                    calcNewQuantityRescue(productOld, productNew),
                    calcNewTotalRescue(productOld, productNew),
                    productNew.color
                )
                mapForPartOfWallet()
            }
        }
    }

    private suspend fun saveExtract(productNew: Product, buttonPressed: String) {
        withContext(Dispatchers.IO) {
            extractDAO.saveExtract(
                ExtractEntity(
                    productNew.broker,
                    productNew.name,
                    productNew.category,
                    productNew.total,
                    productNew.date,
                    buttonPressed
                )
            )
        }
    }

    suspend fun deleteProduct(nameProduct: String) {
        withContext(Dispatchers.IO) {
            productDAO.deleteProduct(nameProduct)
            mapForPartOfWallet()
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
                updateRate(product.name,String
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

    private fun calcNewTotalApplication(productOld: Product, productNew: Product): String {
        return (productNew.total.toDouble() + productOld.total.toDouble()).toString()
    }

    private fun calcNewTotalRescue(productOld: Product, productNew: Product): String {
        return (productOld.total.toDouble() - productNew.total.toDouble()).toString()
    }

    private fun calcNewQuantityApplication(productOld: Product, productNew: Product): String {
        return (productNew.quantity.toDouble() + productOld.quantity.toDouble()).toString()
    }

    private fun calcNewQuantityRescue(productOld: Product, productNew: Product): String {
        return (productOld.quantity.toDouble() - productNew.quantity.toDouble()).toString()
    }

    private fun calcNewPriceApplication(productOld: Product, productNew: Product): String {
        val denominator = calcNewQuantityApplication(productOld, productNew).toDouble()
        return ((productNew.total.toDouble() + productOld.total.toDouble()) / denominator).toString()
    }

    private fun calcNewPriceRescue(productOld: Product, productNew: Product): String {
        val denominator = calcNewQuantityRescue(productOld, productNew).toDouble()
        return ((productOld.total.toDouble() - productNew.total.toDouble()) / denominator).toString()
    }

}