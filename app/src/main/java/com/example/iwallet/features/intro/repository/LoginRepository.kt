package com.example.iwallet.features.intro.repository

import com.example.iwallet.utils.data.local.SessionManager
import com.example.iwallet.utils.model.intro.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LoginRepository(private val sessionManager: SessionManager) {

    fun saveCacheNews(save: Boolean) = sessionManager.saveCacheNews(save)

    fun saveUserLogin(email: String, password: String) =
        sessionManager.saveUserLogin(email, password)

    fun deleteUserLogin() = sessionManager.deleteUserLogin()

    fun getUserEmail(): String = sessionManager.getUserEmail()

    fun getUserPassword(): String = sessionManager.getUserPassword()

    fun saveUserEmailBackup(email: String) = sessionManager.saveUserEmailBackup(email)

    suspend fun loginInFirebase(user: User) {
        withContext(Dispatchers.IO) {
            var erroAuth = false
            FirebaseAuth.getInstance().signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    saveUserEmailBackup(user.email.replace(".com",""))
                }
                .addOnFailureListener {
                    erroAuth = true
                }
            delay(1000L)
            erroAuthFirebase(erroAuth)
        }
    }

    fun erroAuthFirebase(erroAuth: Boolean) {
        if (erroAuth) {
            throw Exception("Usuário Inválido")
        }
    }

}
