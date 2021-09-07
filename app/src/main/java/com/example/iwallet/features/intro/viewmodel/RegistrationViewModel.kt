package com.example.iwallet.features.intro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.intro.repository.RegistrationRepository
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

    private val _showErro = MutableLiveData<String>()
    val showErro: LiveData<String> = _showErro

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
            _showLoading.postValue(true)
            delay(2000L)
            when {
                email.isNullOrBlank() -> {
                    _showErro.postValue("Email não preenchido!")
                }
                password.isNullOrBlank() -> {
                    _showErro.postValue("Senha não preenchida!")
                }
                password!!.length < 6 -> {
                    _showErro.postValue("A senha deve ter mais de 6 caracteres !")
                }
                confirmPassword.isNullOrBlank() -> {
                    _showErro.postValue("Senha de confirmação não preenchida!")
                }
                password != confirmPassword -> {
                    _showErro.postValue("As senhas preechidas são diferentes")
                }
                else -> {
                    _showLoading.postValue(true)
                    try{
                        registrationRepository.signUpInFirebase(User(email!!, password!!))
                        _goToLogin.postValue(Unit)
                    }catch (e:Exception){
                        _showErro.postValue("erro no sistema")
                    }
                    _showLoading.postValue(false)
                }
            }
            _showLoading.postValue(false)
        }
    }

}