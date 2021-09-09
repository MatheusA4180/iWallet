package com.example.iwallet.features.resume.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwallet.R
import com.example.iwallet.utils.clicklistener.ClickListeners

class ListCategoriesAdapter(
    private val listCategories: List<String>,
    private val clickedCategoryListener: ClickListeners.ClickedCategoryListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = listCategories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_categories, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProductViewHolder) {
            holder.bind(listCategories,position)
            holder.itemView.setOnClickListener {
                clickedCategoryListener.clickCategoryListener(position, listCategories[position])
            }
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textCategory: TextView = itemView.findViewById(R.id.text_item_category)

        fun bind(listCategories: List<String>, position: Int) {
            textCategory.text = listCategories[position]
        }

    }

}