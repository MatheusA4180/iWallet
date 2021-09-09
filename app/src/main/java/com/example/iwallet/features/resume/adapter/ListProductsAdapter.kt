package com.example.iwallet.features.resume.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwallet.R
import com.example.iwallet.utils.clicklistener.ClickListeners
import com.example.iwallet.utils.helperfunctions.HelperFunctions.converterToPercent
import com.example.iwallet.utils.helperfunctions.HelperFunctions.converterToReal
import com.example.iwallet.utils.model.resume.Product

class ListProductsAdapter(
    private val listProducts: List<Product>,
    private val clickedProductListener: ClickListeners.ClickedProductListener,
    private val toolbarEnable: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = listProducts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder) {
            holder.bind(listProducts, position)
            holder.setColor(listProducts[position].color.toInt())
            holder.itemView.setOnClickListener {
                if (toolbarEnable) {
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

        private val nameBroker: TextView = itemView.findViewById(R.id.name_broker)
        private val nameProduct: TextView = itemView.findViewById(R.id.name_product)
        private val categoryProduct: TextView = itemView.findViewById(R.id.category_product)
        private val profitability: TextView = itemView.findViewById(R.id.profitability)
        private val balanceProduct: TextView = itemView.findViewById(R.id.balance_product)
        private val bannerProduct: CardView = itemView.findViewById(R.id.banner)

        fun bind(listProducts: List<Product>, position: Int) {
            nameBroker.text = listProducts[position].broker
            nameProduct.text = listProducts[position].name
            categoryProduct.text = listProducts[position].category
            profitability.text = converterToPercent(listProducts[position].rate)
            balanceProduct.text = converterToReal(listProducts[position].total.toDouble())
        }

        fun setColor(color: Int) {
            nameBroker.setTextColor(color)
            nameProduct.setTextColor(color)
            categoryProduct.setTextColor(color)
            balanceProduct.setTextColor(color)
            profitability.setTextColor(color)
            bannerProduct.setCardBackgroundColor(color)
        }

    }

}
