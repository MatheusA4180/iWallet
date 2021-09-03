package com.example.iwallet.features.resume.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.resume.repository.AddOrSubtractProductRepository
import com.example.iwallet.utils.model.resume.Product
import kotlinx.coroutines.launch

class AddOrSubtractProductViewModel(
    private val addOrSubtractProductRepository: AddOrSubtractProductRepository
): ViewModel() {

    private val _listProducts = MutableLiveData<List<Product>>()
    val listProducts: LiveData<List<Product>> = _listProducts

    fun requestListProducts(){
        viewModelScope.launch {
            _listProducts.postValue(addOrSubtractProductRepository.returnListProducts())
        }
    }

}