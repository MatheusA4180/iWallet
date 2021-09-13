package com.example.iwallet.utils.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.iwallet.features.resume.fragments.ThemesNewsFragment.Companion.STOCKS_RADIO

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

    fun saveCacheNews(save: Boolean) {
        preferences.edit {
            putBoolean(CACHE_NEWS, save)
        }
    }

    fun savedCacheNews(): Boolean = preferences.getBoolean(CACHE_NEWS, false)

    fun saveSelectedThemeNews(theme: String) {
        preferences.edit {
            putString(THEME_NEWS, theme)
        }
    }

    fun getSaveThemeNews(): String = preferences.getString(THEME_NEWS, STOCKS_RADIO)!!

    companion object {
        private const val PASS_TO_ONBOARDING = "Passou"
        private const val USER = "Username"
        private const val PASSWORD = "Password"
        private const val CACHE_NEWS = "CacheNews"
        private const val THEME_NEWS = "ThemeNews"
    }

}
