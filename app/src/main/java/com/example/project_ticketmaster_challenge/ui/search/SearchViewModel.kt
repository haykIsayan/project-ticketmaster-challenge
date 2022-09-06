package com.example.project_ticketmaster_challenge.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_ticketmaster_challenge.data.TicketmasterRepository
import com.example.project_ticketmaster_challenge.model.EventModel
import com.example.project_ticketmaster_challenge.ui.ViewModelState
import com.example.project_ticketmaster_challenge.ui.ViewModelState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val ticketmasterRepository: TicketmasterRepository
): ViewModel() {

    private val searchState = MutableLiveData<ViewModelState<List<EventModel>>>()
    fun getSearchState(): LiveData<ViewModelState<List<EventModel>>> = searchState

    private var searchJob: Job? = Job()

    init {
        searchEvents("")
    }

    fun searchEvents(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) { searchState.value = ViewModelStatePending() }
                delay(500)
                val events = ticketmasterRepository.searchDiscoveryEvents(query)
                withContext(Dispatchers.Main) { searchState.value = ViewModelStateIdle(events) }
            } catch (e: Exception) {
                println(e.message)
                withContext(Dispatchers.Main) { searchState.value = ViewModelStateError(e.message) }
            }
        }
    }
}