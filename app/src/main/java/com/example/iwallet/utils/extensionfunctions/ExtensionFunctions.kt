package com.example.iwallet.utils.extensionfunctions

import android.util.Patterns

object ExtensionFunctions {

    fun String.isValidEmail() = !(Patterns.EMAIL_ADDRESS.matcher(this).matches())

}
