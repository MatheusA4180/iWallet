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

    suspend fun deleteProduct(nameProduct: String) {
        withContext(Dispatchers.IO) {
            productDAO.deleteProduct(nameProduct)
            mapForPartOfWallet()
        }
    }

    suspend fun registerUpdateProduct(productNew: Product, buttonPressed: String) {
        withContext(Dispatchers.IO) {
            UpdateProduct(productDAO.searchProduct(productNew.name), productNew, buttonPressed)
        }
    }

    private suspend fun UpdateProduct(
        productOld: Product,
        productNew: Product,
        buttonPressed: String,
    ) {
        saveExtract(productNew, buttonPressed)
        productDAO.updateProduct(
            productOld.name,
            calcNewPriceApplicationOrRescue(productOld, productNew, buttonPressed),
            calcNewQuantityApplicationOrRescue(productOld, productNew, buttonPressed),
            calcNewTotalApplicationOrRescue(productOld, productNew, buttonPressed),
            productNew.color
        )
        mapForPartOfWallet()
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

    private fun calcNewTotalApplicationOrRescue(
        productOld: Product,
        productNew: Product,
        buttonPressed: String
    ): String {
        return when (buttonPressed) {
            APPLICATION -> {
                (productOld.total.toDouble() + productNew.total.toDouble()).toString()
            }
            else -> {
                (productOld.total.toDouble() - productNew.total.toDouble()).toString()
            }
        }
    }

    private fun calcNewQuantityApplicationOrRescue(
        productOld: Product,
        productNew: Product,
        buttonPressed: String
    ): String {
        return when (buttonPressed) {
            APPLICATION -> {
                (productOld.quantity.toDouble() + productNew.quantity.toDouble()).toString()
            }
            else -> {
                (productOld.quantity.toDouble() - productNew.quantity.toDouble()).toString()
            }
        }
    }

    private fun calcNewPriceApplicationOrRescue(
        productOld: Product,
        productNew: Product,
        buttonPressed: String
    ): String {
        val denominator =
            calcNewQuantityApplicationOrRescue(productOld, productNew, buttonPressed).toDouble()
        return when (buttonPressed) {
            APPLICATION -> {
                ((productOld.total.toDouble() + productNew.total.toDouble()) / denominator).toString()
            }
            else -> {
                ((productOld.total.toDouble() - productNew.total.toDouble()) / denominator).toString()
            }
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
                updateRate(product.name, calcAndformatPartOfWallet(product, total))
            }
        }
    }

    private fun calcAndformatPartOfWallet(product: Product, total: Double) =
        String.format("%.2f", ((product.total.toDouble() / total) * 100)).toDouble()

    private suspend fun updateRate(nameProduct: String, rateProduct: Double) {
        withContext(Dispatchers.IO) {
            productDAO.updateRate(
                nameProduct,
                rateProduct.toString()
            )
        }
    }

}
