package com.example.project_ticketmaster_challenge.ui.search

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_ticketmaster_challenge.R
import com.example.project_ticketmaster_challenge.common.ViewModelState
import com.example.project_ticketmaster_challenge.common.ViewModelState.*
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.ui.search.filter.FilterDecorator
import com.example.project_ticketmaster_challenge.ui.search.filter.FiltersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_search.*

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {

    private val filterViewModel by viewModels<FilterViewModel>()
    private val searchViewModel by viewModels<SearchViewModel>()

    private val searchAdapter = SearchAdapter()
    private val filterAdapter = FiltersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        observeEventQuery()
        initSearchEditText()
        initSearchRecyclerView()
        initSegmentsRecyclerView()
        initSegmentsState()
        initSearchState()
    }

    private fun initSearchEditText() {
        searchEventsEditText.addTextChangedListener {
            val text = searchEventsEditText.text.toString()
            filterViewModel.onKeywordChanged(text)
        }
    }

    private fun initSegmentsRecyclerView() {
        filterAdapter.onSegmentSelected { segment ->
            filterViewModel.onFilterSelected(segment)
        }
        segmentRecyclerView.adapter = filterAdapter
        segmentRecyclerView.addItemDecoration(FilterDecorator())
        segmentRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false,
        )
    }


    private fun observeEventQuery() {
        filterViewModel.getEventQuery().observe(this) {
            searchViewModel.searchEvents(it)
        }
    }


    private fun initSegmentsState() {
        filterViewModel.getEventQuery().observe(this) {
            filterAdapter.updateItems(it.filters)
        }
    }

    private fun initSearchRecyclerView() {
        val dividerItemDecoration = DividerItemDecoration(
            searchEventsRecyclerView.context,
            LinearLayoutManager.VERTICAL,
        )
        searchEventsRecyclerView.addItemDecoration(dividerItemDecoration)
        searchEventsRecyclerView.adapter = searchAdapter
        searchEventsRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false,
        )
    }

    private fun initSearchState() {
        searchViewModel.getSearchState().observe(
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
        noSearchResultsTextView.visibility = View.VISIBLE
        searchEventsRecyclerView.visibility = View.GONE
        searchLoadingIndicator.visibility = View.GONE
    }

    private fun onStateIdle(state: ViewModelStateIdle<List<EventModel>>) {
        val discoverEvents = state.value
        searchLoadingIndicator.visibility = View.GONE
        noSearchResultsTextView.visibility = View.GONE
        searchEventsRecyclerView.visibility = View.VISIBLE
        searchAdapter.updateItems(discoverEvents)
    }

    private fun onStatePending() {
        searchEventsRecyclerView.visibility = View.GONE
        searchLoadingIndicator.visibility = View.VISIBLE
        noSearchResultsTextView.visibility = View.GONE
    }
}