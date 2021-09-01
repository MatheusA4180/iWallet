package com.example.iwallet.features.intro.repository

import com.example.iwallet.utils.data.local.SessionManager

class SplashRepository(
    private val sessionManager: SessionManager
) {

    fun passedByTheOnBoarding(): Boolean = sessionManager.passedByTheOnBoarding()

}