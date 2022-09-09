package com.example.project_ticketmaster_challenge.ui.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_ticketmaster_challenge.model.EventModel
import kotlinx.android.synthetic.main.search_event_item_view.view.*

class SearchItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindEvent(event: EventModel) {
        itemView.searchEventName.text = event.name
        initEventClassification(event)
        initEventImage(event)
    }

    private fun initEventClassification(event: EventModel) {
        val classifications = event.classifications
        if (classifications.isNullOrEmpty()) return
        val segment = classifications.first().segment ?: return
        itemView.apply {
            searchEventClassificationTextView.visibility = View.VISIBLE
            searchEventClassificationTextView.text = segment.name
        }
    }

    private fun initEventImage(event: EventModel) {
        val images = event.images
        if (images.isNotEmpty()) {
            Glide.with(itemView)
                .load(event.images.first().url)
                .centerCrop()
                .into(itemView.searchEventImage)
        }
    }
}