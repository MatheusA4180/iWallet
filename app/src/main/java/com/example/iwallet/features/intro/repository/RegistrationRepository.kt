package com.example.iwallet.features.intro.repository

import com.example.iwallet.utils.data.local.SessionManager

class RegistrationRepository(private val sessionManager: SessionManager) {

    fun saveUserRegistration(email: String, password: String) =
        sessionManager.saveUserRegistration(email, password)

}