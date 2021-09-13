package com.example.iwallet.features.manager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwallet.R

class ListFinancesAdapter(
    private val listFinances: List<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = listFinances.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FinancesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_finances, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is FinancesViewHolder){
            holder.bind(listFinances,position)
        }
    }

    class FinancesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val icon: ImageView = itemView.findViewById(R.id.icon_category_finances)
        private val textCategory: TextView = itemView.findViewById(R.id.category_finances)
        private val textBalance: TextView = itemView.findViewById(R.id.balance_finances)
        private val textPercent: TextView = itemView.findViewById(R.id.percent_finances)

        fun bind(listFinances: List<String>, position: Int) {

        }

    }

}
