package com.example.project_ticketmaster_challenge.ui.search

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_ticketmaster_challenge.R
import com.example.project_ticketmaster_challenge.model.EventModel
import com.example.project_ticketmaster_challenge.ui.ViewModelState
import com.example.project_ticketmaster_challenge.ui.ViewModelState.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_search.*

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {

    private val viewModel by viewModels<SearchViewModel>()

    private val searchAdapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initSearchEditText()
        initSearchRecyclerView()
        initSearchState()
    }

    private fun initSearchEditText() {
        searchEventsEditText.addTextChangedListener {
            val text = searchEventsEditText.text.toString()
            viewModel.searchEvents(text)
        }
    }

    private fun initSearchRecyclerView() {
        searchEventsRecyclerView.adapter = searchAdapter
        searchEventsRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false,
        )
    }

    private fun initSearchState() {
        viewModel.getSearchState().observe(
            this,
            ::onSearchState,
        )
    }

    private fun onSearchState(state: ViewModelState<List<EventModel>>) {
        when (state) {
            is ViewModelStateError -> onStateError()
            is ViewModelStateIdle -> onStateIdle(state)
            is ViewModelStatePending -> onStatePending()
        }
    }

    private fun onStateError() {
//        noEventsTextView.visibility = View.VISIBLE
        searchEventsRecyclerView.visibility = View.GONE
        searchLoadingIndicator.visibility = View.GONE
    }

    private fun onStateIdle(state: ViewModelStateIdle<List<EventModel>>) {
        val discoverEvents = state.value
        searchLoadingIndicator.visibility = View.GONE
//        noEventsTextView.visibility = View.GONE
        searchEventsRecyclerView.visibility = View.VISIBLE
        searchAdapter.updateItems(discoverEvents)
    }

    private fun onStatePending() {
        searchEventsRecyclerView.visibility = View.GONE
        searchLoadingIndicator.visibility = View.VISIBLE
//        noEventsTextView.visibility = View.GONE
    }
}