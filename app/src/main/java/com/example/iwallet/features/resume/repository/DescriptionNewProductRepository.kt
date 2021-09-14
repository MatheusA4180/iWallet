package com.example.iwallet.features.resume.repository

import com.example.iwallet.features.resume.fragments.DescriptionProductFragment.Companion.APPLICATION
import com.example.iwallet.features.resume.repository.ResumeRepository.Companion.PATH_EXTRACT
import com.example.iwallet.features.resume.repository.ResumeRepository.Companion.PATH_PRODUCT
import com.example.iwallet.features.resume.repository.ResumeRepository.Companion.PATH_USERS
import com.example.iwallet.utils.data.local.SessionManager
import com.example.iwallet.utils.data.local.database.ExtractDAO
import com.example.iwallet.utils.data.local.database.ProductDAO
import com.example.iwallet.utils.model.resume.Product
import com.example.iwallet.utils.model.resume.ProductEntity
import com.example.iwallet.utils.model.wallet.ExtractEntity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DescriptionNewProductRepository(
    private val sessionManager: SessionManager,
    private val productDAO: ProductDAO,
    private val extractDAO: ExtractDAO
) {

    fun getSaveUserEmailBackup(): String = sessionManager.getSaveUserEmailBackup()

    suspend fun applyRegisterProductInDatabase(product: Product) {
        saveExtract(product)
        withContext(Dispatchers.IO) {
            val product = ProductEntity(
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
            productDAO.saveProduct(product)
            Firebase.database.reference.child(PATH_USERS).child(getSaveUserEmailBackup())
                .child(PATH_PRODUCT).push().setValue(product)
            mapForPartOfWallet()
        }
    }

    private suspend fun saveExtract(productNew: Product) {
        withContext(Dispatchers.IO) {
            val extract = ExtractEntity(
                productNew.broker,
                productNew.name,
                productNew.category,
                productNew.total,
                productNew.date,
                APPLICATION
            )
            extractDAO.saveExtract(extract)
            Firebase.database.reference.child(PATH_USERS).child(getSaveUserEmailBackup())
                .child(PATH_EXTRACT).push().setValue(extract)
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
