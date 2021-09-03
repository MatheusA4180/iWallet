package com.example.iwallet.features.resume.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.resume.repository.DescriptionNewProductRepository
import com.example.iwallet.utils.model.resume.Product
import kotlinx.coroutines.launch

class DescriptionNewProductViewModel(
    private val descriptionNewProductRepository: DescriptionNewProductRepository
): ViewModel() {

    private val product = Product()

    fun changeCategory(category: String) {
        product.category = category
    }

    fun changeNameBroker(nameBroker: String) {
        product.broker = nameBroker
    }

    fun changeNameProduct(nameProduct: String) {
        product.name = nameProduct
    }

    fun changePriceProduct(price: String) {
        product.price = price
    }

    fun changeQuantityProduct(quantity: String) {
        product.quantity = quantity
    }

    fun changeDateProduct(date: String) {
        product.date = date
    }

    fun changeRateProduct(rate: String) {
        product.rate = rate
    }

    fun applyRegisterProduct(){
        viewModelScope.launch {
            descriptionNewProductRepository.applyRegisterProductInDatabase(product)
        }
    }

}