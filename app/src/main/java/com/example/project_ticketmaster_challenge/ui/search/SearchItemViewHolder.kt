package com.example.project_ticketmaster_challenge.ui.search

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_ticketmaster_challenge.R
import com.example.project_ticketmaster_challenge.model.EventModel

class SearchItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindEvent(event: EventModel) {
        itemView.findViewById<TextView>(R.id.searchEventName).text = event.name
        val images = event.images
        if (images.isNotEmpty()) {
            val imageView = itemView.findViewById<AppCompatImageView>(R.id.searchEventImage)
            Glide.with(itemView)
                .load(event.images.first().url)
                .centerCrop()
                .into(imageView)
        }
    }
}