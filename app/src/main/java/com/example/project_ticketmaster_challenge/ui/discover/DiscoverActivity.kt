package com.example.project_ticketmaster_challenge.ui.discover

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_ticketmaster_challenge.R
import com.example.project_ticketmaster_challenge.model.EventModel
import com.example.project_ticketmaster_challenge.ui.ViewModelState
import com.example.project_ticketmaster_challenge.ui.ViewModelState.*
import com.example.project_ticketmaster_challenge.ui.search.SearchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_discover.*

@AndroidEntryPoint
class DiscoverActivity : ComponentActivity() {

    private val discoverViewModel by viewModels<DiscoverViewModel>()
    private val discoverAdapter = DiscoverAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)
        initDiscoverEventsRecyclerView()
        initDiscoverEventsState()
        initSearchEventsButton()
    }

    private fun initDiscoverEventsRecyclerView() {
        discoverEventsRecyclerView.adapter = discoverAdapter
        discoverEventsRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false,
        )
    }

    private fun initDiscoverEventsState() {
        discoverViewModel.getDiscoverState().observe(
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
        discoverAdapter.updateItems(discoverEvents)
    }

    private fun onStatePending() {
        discoverEventsRecyclerView.visibility = View.GONE
        discoverEventsLoadingIndicator.visibility = View.VISIBLE
        noEventsTextView.visibility = View.GONE
    }

    private fun initSearchEventsButton() {
        searchEventsButton.setOnClickListener {
            onSearchEventPressed()
        }
    }

    private fun onSearchEventPressed() {
        val intent = Intent(
            this,
            SearchActivity::class.java,
        )
        startActivity(intent)
    }
}
