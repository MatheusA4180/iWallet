package com.example.iwallet.features.wallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.wallet.repository.ExtractRepository
import com.example.iwallet.utils.model.resume.Product
import com.example.iwallet.utils.model.wallet.Extract
import kotlinx.coroutines.launch

class ExtractViewModel(
    private val extractRepository: ExtractRepository
):ViewModel() {

    private val _listExtracts = MutableLiveData<List<Extract>>()
    val listExtracts: LiveData<List<Extract>> = _listExtracts

    fun requestListExtracts() {
        viewModelScope.launch {
            _listExtracts.postValue(extractRepository.returnListExtracts())
        }
    }


}