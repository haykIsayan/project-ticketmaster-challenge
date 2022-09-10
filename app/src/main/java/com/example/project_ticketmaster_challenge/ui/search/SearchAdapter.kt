package com.example.project_ticketmaster_challenge.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_ticketmaster_challenge.R
import com.example.project_ticketmaster_challenge.model.event.EventModel

class SearchAdapter : RecyclerView.Adapter<SearchItemViewHolder>() {

    private val searchEvents = mutableListOf<EventModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(events: List<EventModel>) {
        searchEvents.clear()
        searchEvents.addAll(events)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.search_item_view,
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