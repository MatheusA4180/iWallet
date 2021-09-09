package com.example.iwallet.features.resume.repository

import com.example.iwallet.utils.data.local.SessionManager

class ThemesNewsRepository(
    private val sessionManager: SessionManager
) {

    fun saveCacheNews(save: Boolean) = sessionManager.saveCacheNews(save)

    fun saveSelectedThemeNews(theme: String) {
        sessionManager.saveSelectedThemeNews(theme)
    }

    fun getSaveThemeNews(): String = sessionManager.getSaveThemeNews()

}
