package com.example.iwallet.features.wallet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwallet.R

class ListExtractAdapter(
    private val listExtracts: List<String>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = listExtracts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ExtractViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_extract, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ExtractViewHolder) {
            //holder.textCategory.text = listCategories[position]
        }
    }

    class ExtractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val extractBanner: CardView = itemView.findViewById(R.id.linear_banner)
        val extractIcon: ImageView = itemView.findViewById(R.id.extract_icon)
        val extractBroker: TextView = itemView.findViewById(R.id.extract_broker)
        val extractCategory: TextView = itemView.findViewById(R.id.extract_category)
        val extractDate: TextView = itemView.findViewById(R.id.extract_date)
        val extractProductDescription: TextView = itemView.findViewById(R.id.extract_product_description)
        val extractValueTransation: TextView = itemView.findViewById(R.id.extract_value_transaction)
    }

    interface ClickedCategoryListener {
        fun clickCategoryListener(positionRecyclerView: Int,category: String)
    }

}