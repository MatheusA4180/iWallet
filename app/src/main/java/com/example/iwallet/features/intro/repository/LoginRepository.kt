package com.example.iwallet.features.intro.repository

import com.example.iwallet.utils.data.local.SessionManager
import com.example.iwallet.utils.model.intro.User
import com.google.firebase.auth.FirebaseAuth

class LoginRepository(private val sessionManager: SessionManager) {

    fun saveCacheNews(save: Boolean) = sessionManager.saveCacheNews(save)

    fun saveUserLogin(email: String, password: String) =
        sessionManager.saveUserLogin(email, password)

    fun deleteUserLogin() = sessionManager.deleteUserLogin()

    fun getUserEmail(): String = sessionManager.getUserEmail()

    fun getUserPassword(): String = sessionManager.getUserPassword()

    fun loginInFirebase(user: User) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.email,user.password)
    }

}
