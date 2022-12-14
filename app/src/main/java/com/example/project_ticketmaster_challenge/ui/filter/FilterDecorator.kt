package com.example.project_ticketmaster_challenge.ui.filter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FilterDecorator:  RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = 20
    }
}