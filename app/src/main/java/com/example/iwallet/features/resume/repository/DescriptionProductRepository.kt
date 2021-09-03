package com.example.iwallet.features.resume.repository

import com.example.iwallet.features.resume.fragments.DescriptionProductFragment
import com.example.iwallet.features.resume.fragments.DescriptionProductFragment.Companion.APPLICATION
import com.example.iwallet.utils.data.local.database.ProductDAO
import com.example.iwallet.utils.model.resume.Product
import com.example.iwallet.utils.model.resume.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DescriptionProductRepository(
    private val productDAO: ProductDAO
    ) {

    suspend fun registerUpdateProduct(productNew: Product,buttonPressed: String) {
        withContext(Dispatchers.IO){
            val productOld = productDAO.searchProduct(productNew.name)
            if(buttonPressed == APPLICATION){
                productDAO.updateProduct(
                    productOld.name,
                    calcNewPriceApplication(productOld,productNew),
                    calcNewQuantityApplication(productOld,productNew),
                    calcNewRateApplication(productOld,productNew)
                )
            }else{
                productDAO.updateProduct(
                    productOld.name,
                    calcNewPriceRescue(productOld,productNew),
                    calcNewQuantityRescue(productOld,productNew),
                    calcNewRateRescue(productOld,productNew)
                )
            }
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