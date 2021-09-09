package com.example.iwallet.features.resume.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwallet.R
import com.example.iwallet.utils.clicklistener.ClickListeners
import com.example.iwallet.utils.model.resume.ItemOptionProfile

class ListOptionsProfileAdapter(
    private val listOptionsProfile: List<ItemOptionProfile>,
    private val clickedProfileListener: ClickListeners.ClickedProfileListener
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
            holder.bind(listOptionsProfile, position)
            holder.itemView.setOnClickListener {
                clickedProfileListener.clickProfileListener(position)
            }
        }
    }

    class CardOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val icon: ImageView = itemView.findViewById(R.id.icon_profile)
        private val title: TextView = itemView.findViewById(R.id.description_profile_item)

        fun bind(listOptionsProfile: List<ItemOptionProfile>, position: Int) {
            icon.setImageResource(listOptionsProfile[position].icon)
            title.text = listOptionsProfile[position].title
        }

    }

}
