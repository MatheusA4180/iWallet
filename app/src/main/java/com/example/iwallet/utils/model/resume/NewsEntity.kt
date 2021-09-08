package com.example.iwallet.utils.model.resume

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
