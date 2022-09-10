package com.example.project_ticketmaster_challenge.ui.search.filter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_ticketmaster_challenge.R
import com.example.project_ticketmaster_challenge.model.filter.FilterModel

class FiltersAdapter: RecyclerView.Adapter<FilterViewHolder>() {

    private var onFilterSelected: ((FilterModel) -> Unit)? = null

    private val items = mutableListOf<FilterModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(filters: List<FilterModel>) {
        items.clear()
        items.addAll(filters)
        notifyDataSetChanged()
    }

    fun onFilterSelected(onFilterSelected: (FilterModel) -> Unit) {
        this.onFilterSelected = onFilterSelected
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.filter_item_view,
            parent,
            false,
        )
        return FilterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val filter = items[position]
        holder.bind(filter, onFilterSelected)
    }

    override fun getItemCount(): Int = items.size

}