package com.example.iwallet.features.resume.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwallet.R
import com.example.iwallet.utils.helperfunctions.HelperFunctions.converterToPercent
import com.example.iwallet.utils.helperfunctions.HelperFunctions.converterToReal
import com.example.iwallet.utils.model.resume.Product

class ListProductCompactAdapter(
    private val listProductsCompact: List<Product>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = listProductsCompact.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductCompactViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product_compact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductCompactViewHolder) {
            holder.bind(listProductsCompact, position)
            holder.setColor(listProductsCompact[position].color.toInt())
        }
    }

    class ProductCompactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textProduct: TextView = itemView.findViewById(R.id.product_compact)
        private val bannerProduct: View = itemView.findViewById(R.id.banner_product_compact)
        private val textBalance: TextView = itemView.findViewById(R.id.balance_product_compact)
        private val textCategory: TextView = itemView.findViewById(R.id.category_product_compact)
        private val textPartOfTotal: TextView = itemView.findViewById(R.id.part_of_total)

        fun bind(listProductsCompact: List<Product>, position: Int) {
            textProduct.text = listProductsCompact[position].name
            textBalance.text = converterToReal(listProductsCompact[position].total.toDouble())
            textPartOfTotal.text = converterToPercent(listProductsCompact[position].rate)
            textCategory.text = listProductsCompact[position].category
        }

        fun setColor(color: Int) {
            bannerProduct.setBackgroundColor(color)
            textProduct.setTextColor(color)
            textBalance.setTextColor(color)
            textPartOfTotal.setTextColor(color)
        }

    }

}
