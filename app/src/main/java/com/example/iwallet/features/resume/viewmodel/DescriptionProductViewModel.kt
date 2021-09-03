package com.example.iwallet.features.resume.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.resume.fragments.DescriptionProductFragment.Companion.APPLICATION
import com.example.iwallet.features.resume.fragments.DescriptionProductFragment.Companion.RESCUE
import com.example.iwallet.features.resume.repository.DescriptionProductRepository
import com.example.iwallet.utils.model.resume.Product
import kotlinx.coroutines.launch

class DescriptionProductViewModel(
    private val descriptionProductRepository: DescriptionProductRepository
): ViewModel() {

    private var buttonPressed:String = APPLICATION
    private var product = Product()

    fun buttonPressedApplication() {
        buttonPressed = APPLICATION
    }

    fun buttonPressedRescue() {
        buttonPressed = RESCUE
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

    fun deleteProduct(nameProduct: String) {
        viewModelScope.launch {
            descriptionProductRepository.deleteProduct(nameProduct)
        }
    }

    fun applyRegisterUpdateProduct(){
        viewModelScope.launch {
            descriptionProductRepository.registerUpdateProduct(product,buttonPressed)
        }
    }

}