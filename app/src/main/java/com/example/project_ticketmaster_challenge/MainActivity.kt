package com.example.project_ticketmaster_challenge

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_ticketmaster_challenge.model.EventModel
import com.example.project_ticketmaster_challenge.ui.DiscoverEventsAdapter
import com.example.project_ticketmaster_challenge.ui.DiscoverEventsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val discoverEventsViewModel by viewModels<DiscoverEventsViewModel>()
    private val discoverEventsAdapter = DiscoverEventsAdapter()

    private lateinit var noEventsTextView: TextView
    private lateinit var discoverEventsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noEventsTextView = findViewById(R.id.noEventsTextView)
        discoverEventsRecyclerView = findViewById(R.id.discoverEventsRecyclerView)
        initDiscoverEventsRecyclerView()

        discoverEventsViewModel.getDiscoverEvents().observe(this, ::onDiscoverEvents)
    }

    private fun initDiscoverEventsRecyclerView() {
        discoverEventsRecyclerView.adapter = discoverEventsAdapter
        discoverEventsRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false,
        )
    }

    private fun onDiscoverEvents(discoverEvents: List<EventModel>) {
        if (discoverEvents.isEmpty()) {
            noEventsTextView.visibility = View.VISIBLE
            discoverEventsRecyclerView.visibility = View.GONE
        } else {
            noEventsTextView.visibility = View.GONE
            discoverEventsRecyclerView.visibility = View.VISIBLE
            discoverEventsAdapter.updateItems(discoverEvents)
        }
    }

}