package com.example.iwallet.features.resume.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwallet.R
import com.example.iwallet.utils.helperfunctions.HelperFunctions.converterToReal
import com.example.iwallet.utils.model.resume.Product

class ListProductCompactAdapter(
    private val listProductsCompact: List<Product>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = listProductsCompact.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductCompactViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product_compact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductCompactViewHolder) {
            holder.textProduct.text = listProductsCompact[position].name
            holder.textBalance.text = converterToReal(listProductsCompact[position].total.toDouble())
            holder.textPartOfTotal.text = "${listProductsCompact[position].rate.toDouble().toString().replace(".",",")} %"
            holder.textCategory.text = listProductsCompact[position].category
            holder.bannerProduct.setBackgroundColor(listProductsCompact[position].color.toInt())
            holder.textProduct.setTextColor(listProductsCompact[position].color.toInt())
            holder.textBalance.setTextColor(listProductsCompact[position].color.toInt())
            holder.textPartOfTotal.setTextColor(listProductsCompact[position].color.toInt())
        }
    }

    class ProductCompactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textProduct: TextView = itemView.findViewById(R.id.product_compact)
        val bannerProduct: View = itemView.findViewById(R.id.banner_product_compact)
        val textBalance: TextView = itemView.findViewById(R.id.balance_product_compact)
        val textCategory: TextView = itemView.findViewById(R.id.category_product_compact)
        val textPartOfTotal: TextView = itemView.findViewById(R.id.part_of_total)
    }

}