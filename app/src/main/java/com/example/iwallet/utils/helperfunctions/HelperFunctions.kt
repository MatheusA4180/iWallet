package com.example.iwallet.utils.helperfunctions

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object HelperFunctions {

    fun converterToReal(value: Double): String =
        NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            .format(value)


    fun formatDate(calendar: Calendar): String =
        SimpleDateFormat("dd/MM/yyyy").format(calendar.time)

    fun parseRealForString(real: String): String = real
        .replace(".", "")
        .replace(",", ".")
        .substring(3)

    fun converterToPercent(number: String): String =
        "${number.replace(".",",")} %"

}
