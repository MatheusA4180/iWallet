package com.example.iwallet.utils.data.local

import android.content.SharedPreferences
import androidx.core.content.edit

class SessionManager(private val preferences: SharedPreferences) {

    fun passedByTheOnBoarding(): Boolean = preferences.getBoolean(PASS_TO_ONBOARDING, false)

    fun saveUserPassOnboarding() {
        preferences.edit {
            putBoolean(PASS_TO_ONBOARDING, true)
        }
    }

    fun saveUserLogin(email: String, password: String) {
        preferences.edit {
            putString(USER, email)
            putString(PASSWORD, password)
        }
    }

    fun deleteUserLogin() {
        preferences.edit {
            putString(USER, "")
            putString(PASSWORD, "")
        }
    }

    fun getUserEmail(): String = preferences.getString(USER, "")!!

    fun getUserPassword(): String = preferences.getString(PASSWORD, "")!!

    fun saveUserRegistration(email: String, password: String) {
        preferences.edit {
            putString(USER_REGISTRATION, email)
            putString(PASSWORD_REGISTRATION, password)
        }
    }

    fun getSaveEmailUserRegistration(): String = preferences.getString(USER_REGISTRATION, "")!!

    fun getSavePasswordUserRegistration(): String =
        preferences.getString(PASSWORD_REGISTRATION, "")!!

    companion object {
        private const val PASS_TO_ONBOARDING = "Passou"
        private const val USER = "Username"
        private const val PASSWORD = "Password"
        private const val USER_REGISTRATION = "user registration"
        private const val PASSWORD_REGISTRATION = "password registration"
    }

}
