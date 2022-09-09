package com.example.project_ticketmaster_challenge.ui.discover

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_ticketmaster_challenge.model.event.EventModel
import kotlinx.android.synthetic.main.discover_event_item_view.view.*

class DiscoverItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindEvent(event: EventModel) {
        itemView.discoverEventNameTextView.text = event.name
        initEventClassification(event)
        initEventDate(event)
        initEventImage(event)
    }

    private fun initEventClassification(event: EventModel) {
        val classifications = event.classifications
        if (classifications.isNullOrEmpty()) return
        val segment = classifications.first().segment ?: return
        itemView.apply {
            discoverEventClassificationTextView.visibility = View.VISIBLE
            discoverEventClassificationTextView.text = segment.name
        }
    }

    private fun initEventDate(event: EventModel)  {
        val dates = event.dates ?: return
        val startDate = dates.start?.localDate
        if (startDate.isNullOrEmpty()) return
        itemView.apply {
            discoverEventDateTextView.visibility = View.VISIBLE
            discoverEventDateTextView.text = startDate
        }
    }

    private fun initEventImage(event: EventModel) {
        val images = event.images ?: return
        if (images.isNotEmpty()) {
            Glide.with(itemView)
                .load(event.images.first().url)
                .centerCrop()
                .into(itemView.discoverEventImageView)
        }
    }
}