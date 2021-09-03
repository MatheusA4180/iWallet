package com.example.iwallet.utils.helperfunctions

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object HelperFunctions {

    fun converterToReal(value: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            .format(value)
    }

    fun formatDate(calendar: Calendar): String =
        SimpleDateFormat("dd/MM/yyyy").format(calendar.time)

}
