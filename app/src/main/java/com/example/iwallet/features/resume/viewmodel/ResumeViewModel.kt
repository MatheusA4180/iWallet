package com.example.iwallet.features.resume.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.resume.repository.ResumeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ResumeViewModel(
    private val resumeRepository: ResumeRepository
): ViewModel() {

    private var sumCurrentBalance = 0.00
    private var sumProfitability = 0.00

    private val _currentBalance = MutableLiveData<Double>()
    val currentBalance: LiveData<Double> = _currentBalance

    private val _profitability = MutableLiveData<Double>()
    val profitability: LiveData<Double> = _profitability

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    fun requestCurrentBalanceAndProfitability(){
        viewModelScope.launch {
            _showLoading.postValue(true)
            delay(1500L)
            resumeRepository.returnListProducts().forEach {
                sumCurrentBalance += it.quantity.toDouble() * it.price.toDouble()
                sumProfitability += it.rate.toDouble()
            }
            _currentBalance.postValue(sumCurrentBalance)
            _profitability.postValue(sumProfitability)
            _showLoading.postValue(false)
        }
    }

}