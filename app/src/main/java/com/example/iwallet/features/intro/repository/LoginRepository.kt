package com.example.iwallet.features.intro.repository

import com.example.iwallet.utils.data.local.SessionManager

class LoginRepository(private val sessionManager: SessionManager) {

    fun saveUserLogin(email: String, password: String) =
        sessionManager.saveUserLogin(email, password)

    fun deleteUserLogin() = sessionManager.deleteUserLogin()

    fun getUserEmail(): String = sessionManager.getUserEmail()

    fun getUserPassword(): String = sessionManager.getUserPassword()

    fun getSaveEmailUserRegistration(): String = sessionManager.getSaveEmailUserRegistration()

    fun getSavePasswordUserRegistration(): String = sessionManager.getSavePasswordUserRegistration()

}