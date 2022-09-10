package com.example.project_ticketmaster_challenge.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.model.filter.FilterQueryModel
import com.example.project_ticketmaster_challenge.common.ViewModelState
import com.example.project_ticketmaster_challenge.common.ViewModelState.*
import com.example.project_ticketmaster_challenge.interactor.SearchEventsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchEventsInteractor: SearchEventsInteractor
): ViewModel() {

    private val searchState = MutableLiveData<ViewModelState<List<EventModel>>>()
    fun getSearchState(): LiveData<ViewModelState<List<EventModel>>> = searchState

    private var searchJob: Job? = Job()

    fun searchEvents(filterQuery: FilterQueryModel) { // todo write a unit test
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) { searchState.value = ViewModelStatePending() }
                delay(500)
                val events = searchEventsInteractor.execute(filterQuery)
                withContext(Dispatchers.Main) { searchState.value = ViewModelStateIdle(events) }
            } catch (e: Exception) {
                println(e.message)
                withContext(Dispatchers.Main) { searchState.value = ViewModelStateError(e.message) }
            }
        }
    }
}