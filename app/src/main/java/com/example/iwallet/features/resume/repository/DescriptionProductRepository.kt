package com.example.iwallet.features.resume.repository

import com.example.iwallet.features.resume.fragments.DescriptionProductFragment.Companion.APPLICATION
import com.example.iwallet.utils.data.local.database.ExtractDAO
import com.example.iwallet.utils.data.local.database.ProductDAO
import com.example.iwallet.utils.model.resume.Product
import com.example.iwallet.utils.model.resume.ProductEntity
import com.example.iwallet.utils.model.wallet.ExtractEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DescriptionProductRepository(
    private val productDAO: ProductDAO,
    private val extractDAO: ExtractDAO
    ) {

    suspend fun registerUpdateProduct(productNew: Product,buttonPressed: String) {
        withContext(Dispatchers.IO){
            val productOld = productDAO.searchProduct(productNew.name)
            if(productNew.price.isEmpty() || productNew.quantity.isEmpty() || productNew.rate.isEmpty()){
                productDAO.updateProduct(
                    productOld.name,
                    productOld.price,
                    productOld.quantity,
                    productOld.rate,
                    productNew.color
                )
            }else{
                if(buttonPressed == APPLICATION){
                    saveExtract(productNew,buttonPressed)
                    productDAO.updateProduct(
                        productOld.name,
                        calcNewPriceApplication(productOld,productNew),
                        calcNewQuantityApplication(productOld,productNew),
                        calcNewRateApplication(productOld,productNew),
                        productNew.color
                    )
                }else{
                    saveExtract(productNew,buttonPressed)
                    productDAO.updateProduct(
                        productOld.name,
                        calcNewPriceRescue(productOld,productNew),
                        calcNewQuantityRescue(productOld,productNew),
                        calcNewRateRescue(productOld,productNew),
                        productNew.color
                    )
                }
            }
        }
    }

    private suspend fun saveExtract(productNew: Product, buttonPressed: String) {
        withContext(Dispatchers.IO){
            extractDAO.saveExtract(
                ExtractEntity(
                    productNew.broker,
                    productNew.name,
                    productNew.category,
                    (productNew.price.toDouble() * productNew.quantity.toDouble()).toString(),
                    productNew.date,
                    buttonPressed
                )
            )
        }
    }

    private fun calcNewRateRescue(productOld: Product, productNew: Product): String {
        return productNew.rate
    }

    private fun calcNewQuantityRescue(productOld: Product, productNew: Product): String {
        return (productOld.quantity.toDouble() - productNew.quantity.toDouble()).toString()
    }

    private fun calcNewPriceRescue(productOld: Product, productNew: Product): String {
        val n1 = productNew.price.toDouble() * productNew.quantity.toDouble()
        val n2 = productOld.price.toDouble() * productOld.quantity.toDouble()
        val n3 = productOld.quantity.toDouble() - productNew.quantity.toDouble()
        return ((n2-n1)/n3).toString()
    }

    private fun calcNewRateApplication(productOld: Product, productNew: Product): String {
        return productNew.rate
    }

    private fun calcNewQuantityApplication(productOld: Product, productNew: Product): String {
        return (productNew.quantity.toDouble() + productOld.quantity.toDouble()).toString()
    }

    private fun calcNewPriceApplication(productOld: Product, productNew: Product): String {
        val n1 = productNew.price.toDouble() * productNew.quantity.toDouble()
        val n2 = productOld.price.toDouble() * productOld.quantity.toDouble()
        val n3 = productNew.quantity.toDouble() + productOld.quantity.toDouble()
        return ((n1+n2)/n3).toString()
    }

    suspend fun deleteProduct(nameProduct: String) {
        withContext(Dispatchers.IO){
            productDAO.deleteProduct(nameProduct)
        }
    }

}