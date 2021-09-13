package com.example.iwallet.features.intro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwallet.features.intro.repository.LoginRepository
import com.example.iwallet.utils.extensionfunctions.ExtensionFunctions.isValidEmail
import com.example.iwallet.utils.model.SingleLiveEvent
import com.example.iwallet.utils.model.intro.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private var email: String? = null
    private var password: String? = null

    private val _goToHome = SingleLiveEvent<Unit>()
    val goToHome: LiveData<Unit> = _goToHome

    private val _showErro = SingleLiveEvent<String>()
    val showErro: LiveData<String> = _showErro

    private val _emailErro = MutableLiveData<String>()
    val emailErro: LiveData<String> = _emailErro

    private val _passwordErro = MutableLiveData<String>()
    val passwordErro: LiveData<String> = _passwordErro

    private val _rememberUserToogle = MutableLiveData<Unit>()
    val rememberUserToogle: LiveData<Unit> = _rememberUserToogle

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    private val _emailSave = MutableLiveData<String>()
    val emailSave: LiveData<String> = _emailSave

    private val _passwordSave = MutableLiveData<String>()
    val passwordSave: LiveData<String> = _passwordSave

    fun onEmailChange(email: String): String {
        this.email = email
        return email
    }

    fun onPasswordChange(password: String): String {
        this.password = password
        return password
    }

    fun onLoginClicked() {
        when {
            email.isNullOrBlank() && password.isNullOrBlank() -> {
                _emailErro.postValue("Email não preenchido!")
                _passwordErro.postValue("Senha não preenchida!")
            }
            email.isNullOrBlank() -> {
                _emailErro.postValue("Email não preenchido!")
            }
            email!!.isValidEmail() -> {
                _emailErro.postValue("Email não é valido!")
            }
            password.isNullOrBlank() -> {
                _passwordErro.postValue("Senha não preenchida!")
            }
            else -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            _showLoading.postValue(true)
            try{
                loginRepository.loginInFirebase(User(email!!,password!!))
                loginRepository.saveCacheNews(false)
                _goToHome.postValue(Unit)
            }catch (e:Exception){
                _showErro.postValue(e.message)
            }
            _showLoading.postValue(false)
        }
    }

    fun onRememberChecked(isChecked: Boolean) {
        if (isChecked) {
            saveLogin()
        } else {
            deleteLogin()
        }
    }

    private fun saveLogin() {
        loginRepository.saveUserLogin(email!!, password!!)
    }

    private fun deleteLogin() {
        loginRepository.deleteUserLogin()
    }

    fun initLogin() {
        email = loginRepository.getUserEmail()
        password = loginRepository.getUserPassword()
        _emailSave.postValue(email!!)
        _passwordSave.postValue(password!!)
        if ((email!!.isNotEmpty()) || (password!!.isNotEmpty())) {
            _rememberUserToogle.postValue(Unit)
        }
    }

}
