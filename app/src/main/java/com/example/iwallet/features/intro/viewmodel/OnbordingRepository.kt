package com.example.iwallet.features.intro.viewmodel

import com.example.iwallet.utils.data.local.SessionManager

class OnbordingRepository(
    private val sessionManager: SessionManager
) {

    fun saveUserPassOnboarding() = sessionManager.saveUserPassOnboarding()

}