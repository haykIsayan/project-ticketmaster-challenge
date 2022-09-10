package com.example.project_ticketmaster_challenge.ui.filter

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.project_ticketmaster_challenge.R
import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import com.example.project_ticketmaster_challenge.model.filter.FilterType
import kotlinx.android.synthetic.main.filter_item_view.view.*

class FilterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(filter: FilterModel, onFilterSelected: ((FilterModel) -> Unit)?) {
        itemView.filterNameTextView.text = filter.name
        setBackgroundColor(filter)
        itemView.setOnClickListener {
            onFilterSelected?.invoke(filter)
        }
    }

    private fun setBackgroundColor(filter: FilterModel) {
        val backgroundColor = getBackgroundColor(filter)
        itemView.filterCardView.backgroundTintList = ColorStateList.valueOf(backgroundColor)
    }

    private fun getBackgroundColor(filter: FilterModel) = if (filter.isApplied) {
        ContextCompat.getColor(itemView.context, R.color.orange)
    } else {
        getNotAppliedColor(filter)
    }

    private fun getNotAppliedColor(filter: FilterModel) = when (filter.type) {
        FilterType.classification ->  ContextCompat.getColor(itemView.context, R.color.purple_700)
        FilterType.sort -> ContextCompat.getColor(itemView.context, R.color.purple_200)
        else -> ContextCompat.getColor(itemView.context, R.color.gray_600)
    }
}
