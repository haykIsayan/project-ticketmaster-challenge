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
import javax.inject.Named

@HiltViewModel
class SearchViewModel @Inject constructor(
    @Named("ioDispatcher")
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    @Named("mainDispatcher")
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val searchEventsInteractor: SearchEventsInteractor
): ViewModel() {

    private val searchState = MutableLiveData<ViewModelState<List<EventModel>>>()
    fun getSearchState(): LiveData<ViewModelState<List<EventModel>>> = searchState

    private var searchJob: Job? = Job()

    fun searchEvents(filterQuery: FilterQueryModel, debounce: Boolean = true) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(mainDispatcher) {
            try {
                searchState.value = ViewModelStatePending()
                if (debounce) delay(500)
                val events = withContext(ioDispatcher) { searchEventsInteractor.execute(filterQuery) }
                searchState.value = if (events.isNotEmpty()) {
                    ViewModelStateIdle(events)
                } else {
                    ViewModelStateEmpty()
                }
            } catch (e: Exception) {
                println(e.message)
                searchState.value = ViewModelStateError(e.message)
            }
        }
    }
}