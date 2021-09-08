package com.example.iwallet.features.intro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.intro.fragments.OnbordingFragment.Companion.LOGIN_CODE
import com.example.iwallet.features.intro.repository.OnbordingRepository
import kotlinx.coroutines.launch

class OnbordingViewModel(
    private val repository: OnbordingRepository
) : ViewModel() {

    private val _goToLogin = MutableLiveData<Unit>()
    val goToLogin: LiveData<Unit> = _goToLogin

    private val _goToRegistration = MutableLiveData<Unit>()
    val goToRegistration: LiveData<Unit> = _goToRegistration

    fun onClickedLoginOrRegistrationStart(screenCode: Int) {
        viewModelScope.launch {
            repository.saveUserPassOnboarding()
            if (screenCode == LOGIN_CODE) {
                _goToLogin.postValue(Unit)
            } else {
                _goToRegistration.postValue(Unit)
            }
        }
    }

}
