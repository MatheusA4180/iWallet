package com.example.iwallet.features.intro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iwallet.features.intro.repository.SplashRepository

class SplashViewModel(
    private val splashRepository: SplashRepository
): ViewModel() {

    private val _splashToOnBoarding = MutableLiveData<Unit>()
    val splashToOnBoarding: LiveData<Unit> = _splashToOnBoarding

    private val _splashToLogin = MutableLiveData<Unit>()
    val splashToLogin: LiveData<Unit> = _splashToLogin

    fun initSplash() {
        if (splashRepository.passedByTheOnBoarding()) {
            _splashToLogin.postValue(Unit)
        } else {
            _splashToOnBoarding.postValue(Unit)
        }
    }

}