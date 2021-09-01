package com.example.iwallet.features.intro.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.intro.viewmodel.OnbordingRepository
import kotlinx.coroutines.launch

class OnbordingViewModel(private val repository: OnbordingRepository) : ViewModel() {

    private val _goToLogin = MutableLiveData<Unit>()
    val goToLogin: LiveData<Unit> = _goToLogin

    fun onClickedLoginStart() {
        viewModelScope.launch {
            repository.saveUserPassOnboarding()
            _goToLogin.postValue(Unit)
        }
    }

}