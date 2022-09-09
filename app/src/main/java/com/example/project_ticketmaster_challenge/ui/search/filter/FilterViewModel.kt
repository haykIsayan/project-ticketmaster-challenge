package com.example.project_ticketmaster_challenge.ui.search.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_ticketmaster_challenge.interactor.ApplyFilterToEventQueryInteractor
import com.example.project_ticketmaster_challenge.interactor.GetDefaultEventQueryInteractor
import com.example.project_ticketmaster_challenge.model.event.EventQueryModel
import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getDefaultEventQueryInteractor: GetDefaultEventQueryInteractor,
    private val applyFilterToEventQueryInteractor: ApplyFilterToEventQueryInteractor
): ViewModel() {

    private val eventQuery = MutableLiveData<EventQueryModel>()
    fun getEventQuery(): LiveData<EventQueryModel> = eventQuery

    init {
        loadEventQuery()
    }

    private fun loadEventQuery() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val query = getDefaultEventQueryInteractor.execute()
                withContext(Dispatchers.Main) { eventQuery.value = query }
            } catch (e: Exception) {

            }
        }
    }

    fun onKeywordChanged(keyword: String) {
        eventQuery.value = eventQuery.value?.copy(keyword = keyword)
    }

    fun onFilterSelected(filter: FilterModel) {
        val currentEventQuery = eventQuery.value ?: return
        eventQuery.value = applyFilterToEventQueryInteractor.execute(currentEventQuery, filter)
    }
}