package com.example.iwallet.utils.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iwallet.utils.model.resume.Product
import com.example.iwallet.utils.model.resume.ProductEntity

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM ProductEntity WHERE name = :nameProduct")
    fun searchProduct(nameProduct: String): Product

    @Query("SELECT * FROM ProductEntity")
    fun returnListProduct(): List<Product>

    @Query("DELETE FROM ProductEntity WHERE name= :nameProduct")
    fun deleteProduct(nameProduct: String)

    @Query("UPDATE ProductEntity SET price=:newPrice,quantity=:newQuantity,rate=:newRate,color=:newColor  WHERE name= :nameProduct")
    fun updateProduct(
        nameProduct: String,
        newPrice: String,
        newQuantity: String,
        newRate: String,
        newColor: String
    )

}