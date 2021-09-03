package com.example.iwallet.features.resume.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwallet.R

class ListOptionsProfileAdapter(
    private val listOptionsProfile: List<ItemOptionProfile>,
    private val clickedProfileListener: ClickedProfileListener
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = listOptionsProfile.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardOptionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CardOptionViewHolder) {
            holder.icon.setImageResource(listOptionsProfile[position].icon)
            holder.title.text = listOptionsProfile[position].title
            holder.itemView.setOnClickListener {
                clickedProfileListener.clickProfileListener(position)
            }
        }
    }

    class CardOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.icon_profile)
        val title: TextView = itemView.findViewById(R.id.description_profile_item)
    }

    data class ItemOptionProfile(val title: String, val icon: Int)

    interface ClickedProfileListener {
        fun clickProfileListener(positionRecyclerView: Int)
    }

}
