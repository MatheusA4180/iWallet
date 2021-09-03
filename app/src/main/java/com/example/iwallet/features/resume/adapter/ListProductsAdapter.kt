package com.example.iwallet.features.resume.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwallet.R
import com.example.iwallet.utils.model.resume.Product

class ListProductsAdapter(
    private val listProducts: List<Product>,
    private val clickedProductListener: ClickedProductListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = listProducts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder) {
            holder.nameBroker.text = listProducts[position].broker
            holder.nameProduct.text = listProducts[position].name
            holder.categoryProduct.text = listProducts[position].category
            holder.profitability.text =
                "${listProducts[position].rate.toDouble().toString().replace(".",",")} %"
            val balance = listProducts[position].quantity.toDouble() * listProducts[position].price.toDouble()
            holder.balanceProduct.text =
                "R$ ${balance.toString().replace(".", ",")}"
            holder.itemView.setOnClickListener {
                clickedProductListener.clickProductListener(position,
                    listProducts[position].broker,listProducts[position].name)
            }
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameBroker: TextView = itemView.findViewById(R.id.name_broker)
        val nameProduct: TextView = itemView.findViewById(R.id.name_product)
        val categoryProduct: TextView = itemView.findViewById(R.id.category_product)
        val profitability: TextView = itemView.findViewById(R.id.profitability)
        val balanceProduct: TextView = itemView.findViewById(R.id.balance_product)
    }

    interface ClickedProductListener {
        fun clickProductListener(positionRecyclerView: Int,nameBroker: String,nameProduct: String)
    }

}