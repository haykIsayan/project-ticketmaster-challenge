package com.example.project_ticketmaster_challenge.ui.search

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_ticketmaster_challenge.R
import com.example.project_ticketmaster_challenge.common.ViewModelState
import com.example.project_ticketmaster_challenge.common.ViewModelState.*
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.ui.filter.FilterDecorator
import com.example.project_ticketmaster_challenge.ui.filter.FilterViewModel
import com.example.project_ticketmaster_challenge.ui.filter.FiltersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_search.*

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {

    private val filterViewModel by viewModels<FilterViewModel>()
    private val searchViewModel by viewModels<SearchViewModel>()

    private val searchAdapter = SearchAdapter()
    private val classificationFilterAdapter = FiltersAdapter()
    private val sortFilterAdapter = FiltersAdapter()
    private val countryCodeFilterAdapter = FiltersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        observeEventQuery()
        initSearchEditText()
        initSearchRecyclerView()
        initFilters()
        initSegmentsState()
        initSearchState()
    }

    private fun initSearchEditText() {
        searchEventsEditText.addTextChangedListener {
            val text = searchEventsEditText.text.toString()
            filterViewModel.onKeywordChanged(text)
        }
    }

    private fun initFilters() {
        initFiltersRecyclerView(
            classificationFilterRecyclerView,
            classificationFilterAdapter
        )
        initFiltersRecyclerView(
            sortFilterRecyclerView,
            sortFilterAdapter
        )
        initFiltersRecyclerView(
            countryCodeRecyclerView,
            countryCodeFilterAdapter
        )
    }

    private fun initFiltersRecyclerView(
        filterRecyclerView: RecyclerView,
        filtersAdapter: FiltersAdapter
    ) {
        filtersAdapter.onFilterSelected { filter ->
            filterViewModel.onFilterSelected(filter)
        }
        filterRecyclerView.adapter = filtersAdapter
        filterRecyclerView.addItemDecoration(FilterDecorator())
        filterRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false,
        )
    }


    private fun observeEventQuery() {
        filterViewModel.getFilterQuery().observe(this) {
            searchViewModel.searchEvents(it)
        }
    }


    private fun initSegmentsState() {
        filterViewModel.getFilterQuery().observe(this) {
            classificationFilterAdapter.updateItems(it.classificationFilters)
            sortFilterAdapter.updateItems(it.sortFilters)
            countryCodeFilterAdapter.updateItems(it.countryCodeFilters)
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