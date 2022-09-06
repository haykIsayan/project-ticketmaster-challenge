package com.example.project_ticketmaster_challenge.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_ticketmaster_challenge.R
import com.example.project_ticketmaster_challenge.model.EventModel

class SearchAdapter : RecyclerView.Adapter<SearchItemViewHolder>() {

    private val searchEvents = mutableListOf<EventModel>()

    fun updateItems(events: List<EventModel>) {
        searchEvents.clear()
        searchEvents.addAll(events)
        notifyItemRangeChanged(0, searchEvents.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.search_event_item_view,
            parent,
            false,
        )
        return SearchItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        val event = searchEvents[position]
        holder.bindEvent(event)
    }

    override fun getItemCount(): Int = searchEvents.size

}