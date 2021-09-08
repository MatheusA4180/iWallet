package com.example.iwallet.features.intro.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.intro.repository.RegistrationRepository
import com.example.iwallet.utils.extensionfunctions.ExtensionFunctions.isValidEmail
import com.example.iwallet.utils.model.intro.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class RegistrationViewModel(
    private val registrationRepository: RegistrationRepository
) : ViewModel() {

    private var email: String? = null
    private var password: String? = null
    private var confirmPassword: String? = null

    private val _goToLogin = MutableLiveData<Unit>()
    val goToLogin: LiveData<Unit> = _goToLogin

    private val _showErroEmail = MutableLiveData<String>()
    val showErroEmail: LiveData<String> = _showErroEmail

    private val _showErro = MutableLiveData<String>()
    val showErro: LiveData<String> = _showErro

    private val _showErroPassword = MutableLiveData<String>()
    val showErroPassword: LiveData<String> = _showErroPassword

    private val _showErroPasswordConfirm = MutableLiveData<String>()
    val showErroPasswordConfirm: LiveData<String> = _showErroPasswordConfirm

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    fun onEmailChange(email: String): String {
        this.email = email
        return email
    }

    fun onPasswordChange(password: String) {
        this.password = password
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        this.confirmPassword = confirmPassword
    }

    fun onClickRegistrationConfirm() {
        viewModelScope.launch {
            when {
                email.isNullOrBlank() -> {
                    _showErroEmail.postValue("Email não preenchido!")
                }
                email!!.isValidEmail() ->{
                    _showErroEmail.postValue("Email não é valido!")
                }
                password.isNullOrBlank() -> {
                    _showErroPassword.postValue("Senha não preenchida!")
                }
                password!!.length < 6 -> {
                    _showErroPassword.postValue("A senha deve ter mais de 6 caracteres !")
                }
                confirmPassword.isNullOrBlank() -> {
                    _showErroPasswordConfirm.postValue("Senha de confirmação não preenchida!")
                }
                password != confirmPassword -> {
                    _showErroPassword.postValue("As senhas preechidas são diferentes")
                    _showErroPasswordConfirm.postValue("As senhas preechidas são diferentes")
                }
                else -> {
                    _showLoading.postValue(true)
                    try{
                        delay(500L)
                        registrationRepository.signUpInFirebase(User(email!!, password!!))
                        _goToLogin.postValue(Unit)
                    }catch (e:Exception){
                        _showErro.postValue("erro no sistema")
                    }
                    _showLoading.postValue(false)
                }
            }
        }
    }

}
