package com.example.iwallet.features.resume.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.resume.fragments.DescriptionNewProductFragmentArgs
import com.example.iwallet.features.resume.repository.DescriptionNewProductRepository
import com.example.iwallet.utils.helperfunctions.HelperFunctions.parseRealForString
import com.example.iwallet.utils.model.resume.Product
import kotlinx.coroutines.launch

class DescriptionNewProductViewModel(
    private val descriptionNewProductRepository: DescriptionNewProductRepository,
    arguments: DescriptionNewProductFragmentArgs
) : ViewModel() {

    private val product = Product(
        category = arguments.category
    )

    private val _showErroBroker = MutableLiveData<String>()
    val showErroBroker: LiveData<String> = _showErroBroker

    private val _showErroNameProduct = MutableLiveData<String>()
    val showErroNameProduct: LiveData<String> = _showErroNameProduct

    private val _showErroPrice = MutableLiveData<String>()
    val showErroPrice: LiveData<String> = _showErroPrice

    private val _showErroColorOrDate = MutableLiveData<String>()
    val showErroColorOrDate: LiveData<String> = _showErroColorOrDate

    private val _confirmRegistration = MutableLiveData<Unit>()
    val confirmRegistration: LiveData<Unit> = _confirmRegistration

    fun changeNameBroker(nameBroker: String) {
        product.broker = nameBroker
    }

    fun changeNameProduct(nameProduct: String) {
        product.name = nameProduct
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

    fun applyRegisterProduct() {
        viewModelScope.launch {
            when {
                product.broker.isEmpty() -> {
                    _showErroBroker.postValue("Nome da corretora não preechida")
                }
                product.name.isEmpty() -> {
                    _showErroNameProduct.postValue("Nome do produto não preechido")
                }
                product.price.isEmpty() -> {
                    _showErroPrice.postValue("Cotação não preechida")
                }
                product.price.toDouble() == 0.0 -> {
                    _showErroPrice.postValue("Valor de cotação inválido")
                }
                product.date.isEmpty() -> {
                    _showErroColorOrDate.postValue("Data não selecionada")
                }
                product.color.isEmpty() -> {
                    _showErroColorOrDate.postValue("Cor não selecionada")
                }
                else -> {
                    product.total =
                        (product.quantity.toDouble() * product.price.toDouble()).toString()
                    descriptionNewProductRepository.applyRegisterProductInDatabase(product)
                    _confirmRegistration.postValue(Unit)
                }
            }
        }
    }

}
