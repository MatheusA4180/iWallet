package com.example.iwallet.features.intro.repository

import com.example.iwallet.utils.model.intro.User
import com.google.firebase.auth.FirebaseAuth

class RegistrationRepository() {

    fun signUpInFirebase(user: User) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email,user.password)
    }

}
