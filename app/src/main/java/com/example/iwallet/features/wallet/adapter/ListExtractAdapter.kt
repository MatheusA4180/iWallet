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
import com.example.iwallet.utils.helperfunctions.HelperFunctions.converterToReal
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
            holder.bind(listExtracts,position)
            if(listExtracts[position].type == APPLICATION){
                holder.setColorAndIcon(R.color.green_Transaction,context,R.drawable.ic_application)
            }else{
                holder.setColorAndIcon(R.color.red_Transaction,context,R.drawable.ic_rescue)
            }
        }
    }

    class ExtractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val extractBanner: CardView = itemView.findViewById(R.id.linear_banner)
        private val extractIcon: ImageView = itemView.findViewById(R.id.extract_icon)
        private val extractBroker: TextView = itemView.findViewById(R.id.extract_broker)
        private val extractCategory: TextView = itemView.findViewById(R.id.extract_category)
        private val extractDate: TextView = itemView.findViewById(R.id.extract_date)
        private val extractProductDescription: TextView = itemView.findViewById(R.id.extract_product_description)
        private val extractValueTransation: TextView = itemView.findViewById(R.id.extract_value_transaction)

        fun bind(listExtracts: List<Extract>, position: Int) {
            extractBroker.text = listExtracts[position].broker
            extractCategory.text = listExtracts[position].category
            extractDate.text = listExtracts[position].date
            extractProductDescription.text = listExtracts[position].name
            extractValueTransation.text = converterToReal(listExtracts[position].balance.toDouble())
        }

        fun setColorAndIcon(color: Int, context: Context, icon: Int) {
            extractProductDescription.setTextColor(ContextCompat
                .getColor(context, color))
            extractValueTransation.setTextColor(ContextCompat
                .getColor(context, color))
            extractIcon.setImageResource(icon)
            extractIcon.drawable.setTint(ContextCompat
                .getColor(context, color))
            extractBanner.setCardBackgroundColor(ContextCompat
                .getColor(context, color))
        }


    }

}