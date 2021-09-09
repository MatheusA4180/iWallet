package com.example.iwallet.features.resume.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.resume.repository.ResumeRepository
import com.example.iwallet.utils.model.resume.DataAndColorsGraph
import com.example.iwallet.utils.model.resume.Product
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.launch

class ResumeViewModel(
    private val resumeRepository: ResumeRepository
) : ViewModel() {

    private val _currentBalance = MutableLiveData<Double>()
    val currentBalance: LiveData<Double> = _currentBalance

    private val _profitability = MutableLiveData<Double>()
    val profitability: LiveData<Double> = _profitability

    private val _listProducts = MutableLiveData<List<Product>>()
    val listProducts: LiveData<List<Product>> = _listProducts

    private val _dataAndColorsGraph = MutableLiveData<DataAndColorsGraph>()
    val dataAndColorsGraph: LiveData<DataAndColorsGraph> = _dataAndColorsGraph

    fun requestListProducts() {
        viewModelScope.launch {
            _listProducts.postValue(resumeRepository.returnListProducts())
        }
    }

    fun requestCurrentBalanceAndProfitability() {
        viewModelScope.launch {
            val listProductsResponse = resumeRepository.returnListProducts()
            var sumCurrentBalance = 0.0
            var sumProfitability = 0.0
            listProductsResponse.forEach {
                sumCurrentBalance += it.total.toDouble()
                sumProfitability += it.rate.toDouble()
            }
            _currentBalance.postValue(sumCurrentBalance)
            _profitability.postValue(sumProfitability)
        }
    }

    fun requestDataForGraph() {
        viewModelScope.launch {
            val listProductsResponse = resumeRepository.returnListProducts()
            val productsTotal: MutableMap<String, Int> = HashMap()
            val colors: ArrayList<Int> = ArrayList()
            val pieEntries: ArrayList<PieEntry> = ArrayList()

            listProductsResponse.forEachIndexed { index, product ->
                productsTotal[product.name] = product.total.toDouble().toInt()
                colors.add(product.color.toInt())
            }

            for (type in productsTotal.keys) {
                pieEntries.add(PieEntry(productsTotal[type]!!.toFloat()))
            }

            _dataAndColorsGraph.postValue(DataAndColorsGraph(pieEntries, colors))
        }
    }

}
