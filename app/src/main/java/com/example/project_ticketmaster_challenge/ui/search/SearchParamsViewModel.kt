package com.example.project_ticketmaster_challenge.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_ticketmaster_challenge.interactor.AddFilterToEventQueryInteractor
import com.example.project_ticketmaster_challenge.interactor.GetEventFiltersInteractor
import com.example.project_ticketmaster_challenge.interactor.UpdateAppliedFiltersInteractor
import com.example.project_ticketmaster_challenge.model.EventQueryModel
import com.example.project_ticketmaster_challenge.model.FilterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchParamsViewModel @Inject constructor(
    private val getEventFiltersInteractor: GetEventFiltersInteractor,
    private val addFilterToEventQueryInteractor: AddFilterToEventQueryInteractor,
    private val updateAppliedFiltersInteractor: UpdateAppliedFiltersInteractor
): ViewModel() {

    val eventQuery = MutableLiveData(EventQueryModel())

    private val filters = MutableLiveData<List<FilterModel>>()
    fun getFilters(): LiveData<List<FilterModel>> = filters

    init {
        loadFilters()
    }

    private fun loadFilters() {
        filters.value = getEventFiltersInteractor.execute()
    }

    fun onKeywordChanged(keyword: String) {
        eventQuery.value = eventQuery.value?.copy(keyword = keyword)
    }

    fun onFilterSelected(filter: FilterModel) {
        val updatedFilter = filter.copy(isApplied = !filter.isApplied)
        updateAppliedFilters(updatedFilter)
        addFilterToEventQuery(updatedFilter)
    }

    private fun updateAppliedFilters(filter: FilterModel) {
        val currentFilters = filters.value ?: return
        val updatedFilters =  updateAppliedFiltersInteractor.execute(currentFilters, filter)
        filters.value = updatedFilters
    }

    private fun addFilterToEventQuery(filter: FilterModel) {
        val currentEventQuery = eventQuery.value ?: return
        eventQuery.value = addFilterToEventQueryInteractor.execute(currentEventQuery, filter)
    }
}