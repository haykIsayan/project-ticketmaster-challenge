package com.example.project_ticketmaster_challenge.ui.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_ticketmaster_challenge.R
import com.example.project_ticketmaster_challenge.model.EventModel

class DiscoverAdapter: RecyclerView.Adapter<DiscoverItemViewHolder>() {

    private val discoverEvents = mutableListOf<EventModel>()

    fun updateItems(events: List<EventModel>) {
        discoverEvents.clear()
        discoverEvents.addAll(events)
        notifyItemRangeChanged(0, discoverEvents.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.discover_event_item_view,
            parent,
            false,
        )
       return DiscoverItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DiscoverItemViewHolder, position: Int) {
        val event = discoverEvents[position]
        holder.bindEvent(event)
    }

    override fun getItemCount(): Int = discoverEvents.size
}
