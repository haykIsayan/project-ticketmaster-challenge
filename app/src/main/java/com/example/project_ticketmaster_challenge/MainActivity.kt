package com.example.project_ticketmaster_challenge

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_ticketmaster_challenge.model.EventModel
import com.example.project_ticketmaster_challenge.ui.DiscoverEventsAdapter
import com.example.project_ticketmaster_challenge.ui.DiscoverEventsViewModel
import com.example.project_ticketmaster_challenge.ui.ViewModelState
import com.example.project_ticketmaster_challenge.ui.ViewModelState.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val discoverEventsViewModel by viewModels<DiscoverEventsViewModel>()
    private val discoverEventsAdapter = DiscoverEventsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDiscoverEventsRecyclerView()
        initDiscoverEventsState()
    }

    private fun initDiscoverEventsRecyclerView() {
        discoverEventsRecyclerView.adapter = discoverEventsAdapter
        discoverEventsRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false,
        )
    }

    private fun initDiscoverEventsState() {
        discoverEventsViewModel.getDiscoverEvents().observe(
            this,
            ::onDiscoverEventsState,
        )
    }

    private fun onDiscoverEventsState(state: ViewModelState<List<EventModel>>) {
        when (state) {
            is ViewModelStateError -> onStateError()
            is ViewModelStateIdle -> onStateIdle(state)
            is ViewModelStatePending -> onStatePending()
        }
    }

    private fun onStateError() {
        noEventsTextView.visibility = View.VISIBLE
        discoverEventsRecyclerView.visibility = View.GONE
        discoverEventsLoadingIndicator.visibility = View.GONE
    }

    private fun onStateIdle(state: ViewModelStateIdle<List<EventModel>>) {
        val discoverEvents = state.value
        discoverEventsLoadingIndicator.visibility = View.GONE
        noEventsTextView.visibility = View.GONE
        discoverEventsRecyclerView.visibility = View.VISIBLE
        discoverEventsAdapter.updateItems(discoverEvents)
    }

    private fun onStatePending() {
        discoverEventsRecyclerView.visibility = View.GONE
        discoverEventsLoadingIndicator.visibility = View.VISIBLE
        noEventsTextView.visibility = View.GONE
    }
}
