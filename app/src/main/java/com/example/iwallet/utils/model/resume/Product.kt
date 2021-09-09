package com.example.iwallet.utils.model.resume

data class Product(
    var broker: String = "",
    var name: String = "",
    var category: String = "",
    var price: String = "0.0",
    var quantity: String = "1.0",
    var total: String = "0.0",
    var date: String = "",
    var rate: String = "0.0",
    var color: String = ""
)
