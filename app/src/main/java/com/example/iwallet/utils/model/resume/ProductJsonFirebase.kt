package com.example.iwallet.utils.model.resume

data class ProductJsonFirebase(
    val key : List<ProductFirebase>
){
    data class ProductFirebase(
        val broker: String = "",
        val name: String = "",
        val category: String = "",
        val price: String = "0.0",
        val quantity: String = "1.0",
        val total: String = "0.0",
        val date: String = "",
        val rate: String = "0.0",
        val color: String = ""
    )
}