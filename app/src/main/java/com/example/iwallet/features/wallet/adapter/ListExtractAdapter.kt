package com.example.iwallet.features.wallet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.iwallet.R
import com.example.iwallet.features.resume.fragments.DescriptionProductFragment.Companion.APPLICATION
import com.example.iwallet.utils.model.wallet.Extract

class ListExtractAdapter(
    private val listExtracts: List<Extract>,
    private val context: Context
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
            holder.extractBroker.text = listExtracts[position].broker
            holder.extractCategory.text = listExtracts[position].category
            holder.extractDate.text = listExtracts[position].date
            holder.extractProductDescription.text = listExtracts[position].name
            holder.extractValueTransation.text = listExtracts[position].balance
            if(listExtracts[position].type == APPLICATION){
                holder.extractProductDescription.setTextColor(ContextCompat
                    .getColor(context, R.color.green_Transaction))
                holder.extractValueTransation.setTextColor(ContextCompat
                    .getColor(context, R.color.green_Transaction))
                holder.extractIcon.setImageResource(R.drawable.ic_application)
                holder.extractIcon.drawable.setTint(ContextCompat
                    .getColor(context, R.color.green_Transaction))
                holder.extractBanner.setCardBackgroundColor(ContextCompat
                    .getColor(context, R.color.green_Transaction))
            }else{
                holder.extractProductDescription.setTextColor(ContextCompat
                    .getColor(context, R.color.red_Transaction))
                holder.extractValueTransation.setTextColor(ContextCompat
                    .getColor(context, R.color.red_Transaction))
                holder.extractIcon.setImageResource(R.drawable.ic_rescue)
                holder.extractIcon.drawable.setTint(ContextCompat
                    .getColor(context, R.color.red_Transaction))
                holder.extractBanner.setCardBackgroundColor(ContextCompat
                    .getColor(context, R.color.red_Transaction))
            }
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

}