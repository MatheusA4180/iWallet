package com.example.iwallet.features.resume.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.resume.fragments.DescriptionProductFragment.Companion.APPLICATION
import com.example.iwallet.features.resume.fragments.DescriptionProductFragment.Companion.RESCUE
import com.example.iwallet.features.resume.fragments.DescriptionProductFragmentArgs
import com.example.iwallet.features.resume.repository.DescriptionProductRepository
import com.example.iwallet.utils.helperfunctions.HelperFunctions.parseRealForString
import com.example.iwallet.utils.model.resume.Product
import kotlinx.coroutines.launch

class DescriptionProductViewModel(
    private val descriptionProductRepository: DescriptionProductRepository,
    arguments: DescriptionProductFragmentArgs
) : ViewModel() {

    private var buttonPressed: String = APPLICATION
    private var product = Product(
        name = arguments.nameProduct,
        broker = arguments.nameBroker,
        category = arguments.category,
        color = arguments.color,
    )

    private val _showErroPrice = MutableLiveData<String>()
    val showErroPrice: LiveData<String> = _showErroPrice

    private val _showErroDate = MutableLiveData<String>()
    val showErroDate: LiveData<String> = _showErroDate

    private val _confirmRegistration = MutableLiveData<Unit>()
    val confirmRegistration: LiveData<Unit> = _confirmRegistration

    fun buttonPressedApplication() {
        buttonPressed = APPLICATION
    }

    fun buttonPressedRescue() {
        buttonPressed = RESCUE
    }

    fun changePriceProduct(price: String) {
        product.price = parseRealForString(price)
    }

    fun changeQuantityProduct(quantity: String) {
        product.quantity = quantity
    }

    fun changeDateProduct(date: String) {
        product.date = date
    }

    fun changeColorProduct(selectedColor: Int) {
        product.color = selectedColor.toString()
    }

    fun deleteProduct(nameProduct: String) {
        viewModelScope.launch {
            descriptionProductRepository.deleteProduct(nameProduct)
        }
    }

    fun applyRegisterUpdateProduct() {
        viewModelScope.launch {
            when {
                product.price.isEmpty() -> {
                    _showErroPrice.postValue("Cotação não preechida")
                }
                product.price.toDouble() == 0.0 -> {
                    _showErroPrice.postValue("Valor de cotação inválido")
                }
                product.date.isEmpty() -> {
                    _showErroDate.postValue("Data não selecionada")
                }
                else -> {
                    product.total =
                        (product.quantity.toDouble() * product.price.toDouble()).toString()
                    descriptionProductRepository.registerUpdateProduct(product, buttonPressed)
                    _confirmRegistration.postValue(Unit)
                }
            }

        }
    }

}
