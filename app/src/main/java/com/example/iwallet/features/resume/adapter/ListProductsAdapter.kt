package com.example.iwallet.features.resume.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwallet.R
import com.example.iwallet.utils.helperfunctions.HelperFunctions.converterToReal
import com.example.iwallet.utils.model.resume.Product

class ListProductsAdapter(
    private val listProducts: List<Product>,
    private val clickedProductListener: ClickedProductListener,
    private val toolbarEnable: Boolean
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
            holder.balanceProduct.text = converterToReal(listProducts[position].total.toDouble())
            holder.nameBroker.setTextColor(listProducts[position].color.toInt())
            holder.nameProduct.setTextColor(listProducts[position].color.toInt())
            holder.categoryProduct.setTextColor(listProducts[position].color.toInt())
            holder.balanceProduct.setTextColor(listProducts[position].color.toInt())
            holder.profitability.setTextColor(listProducts[position].color.toInt())
            holder.bannerProduct.setCardBackgroundColor(listProducts[position].color.toInt())

            holder.itemView.setOnClickListener {
                if(toolbarEnable){
                    clickedProductListener.clickProductListener(
                        listProducts[position].broker,
                        listProducts[position].name,
                        listProducts[position].category,
                        listProducts[position].color
                    )
                }
            }
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameBroker: TextView = itemView.findViewById(R.id.name_broker)
        val nameProduct: TextView = itemView.findViewById(R.id.name_product)
        val categoryProduct: TextView = itemView.findViewById(R.id.category_product)
        val profitability: TextView = itemView.findViewById(R.id.profitability)
        val balanceProduct: TextView = itemView.findViewById(R.id.balance_product)
        val bannerProduct: CardView = itemView.findViewById(R.id.banner)
    }

    interface ClickedProductListener {
        fun clickProductListener(
            nameBroker: String,
            nameProduct: String,
            category : String,
            color: String
        )
    }

}