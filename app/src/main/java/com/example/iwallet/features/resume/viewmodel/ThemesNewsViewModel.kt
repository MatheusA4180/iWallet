package com.example.iwallet.features.resume.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iwallet.R
import com.example.iwallet.features.resume.fragments.ThemesNewsFragment.Companion.CRIPTO_RADIO
import com.example.iwallet.features.resume.fragments.ThemesNewsFragment.Companion.REITS_RADIO
import com.example.iwallet.features.resume.fragments.ThemesNewsFragment.Companion.STOCKS_RADIO
import com.example.iwallet.features.resume.fragments.ThemesNewsFragment.Companion.WELFARE_RADIO
import com.example.iwallet.features.resume.repository.ThemesNewsRepository

class ThemesNewsViewModel(
    private val themesNewsRepository: ThemesNewsRepository
) : ViewModel() {

    private val _selectedTheme = MutableLiveData<Int>()
    val selectedTheme: LiveData<Int> = _selectedTheme

    fun selectedThemeNews(theme: String) {
        themesNewsRepository.saveCacheNews(false)
        themesNewsRepository.saveSelectedThemeNews(theme)
    }

    fun requestThemeSelected() {
        when (themesNewsRepository.getSaveThemeNews()) {
            CRIPTO_RADIO -> {
                _selectedTheme.postValue(R.id.cripto_radio)
            }
            STOCKS_RADIO -> {
                _selectedTheme.postValue(R.id.stocks_radio)
            }
            REITS_RADIO -> {
                _selectedTheme.postValue(R.id.reits_radio)
            }
            WELFARE_RADIO -> {
                _selectedTheme.postValue(R.id.welfare_radio)
            }
        }
    }

}
