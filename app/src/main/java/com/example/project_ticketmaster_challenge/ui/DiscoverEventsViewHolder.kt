package com.example.project_ticketmaster_challenge.ui

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_ticketmaster_challenge.R
import com.example.project_ticketmaster_challenge.model.EventModel

class DiscoverEventsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindEvent(event: EventModel) {
        itemView.findViewById<TextView>(R.id.discoverEventNameTextView).text = event.name
        itemView.findViewById<TextView>(R.id.discoverEventTypeTextView).text = event.type
        val images = event.images
        if (images.isNotEmpty()) {
            val imageView = itemView.findViewById<AppCompatImageView>(R.id.discoverEventImageView)
            Glide.with(itemView)
                .load(event.images.first().url)
                .centerCrop()
                .into(imageView)
        }
    }
}