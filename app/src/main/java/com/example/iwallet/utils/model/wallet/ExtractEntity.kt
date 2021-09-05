package com.example.iwallet.utils.model.wallet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExtractEntity(
    val broker: String = "",
    val name: String = "",
    val category: String = "",
    val balance: String = "0.0",
    val date: String = "",
    val type: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}