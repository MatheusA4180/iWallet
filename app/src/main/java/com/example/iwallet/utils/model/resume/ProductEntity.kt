package com.example.iwallet.utils.model.resume

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    val broker: String = "",
    val name: String = "",
    val category: String = "",
    val price: String = "",
    val quantity: String = "",
    val date: String = "",
    val rate: String = ""
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}