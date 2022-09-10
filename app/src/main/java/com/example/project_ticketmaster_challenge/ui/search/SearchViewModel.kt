package com.example.project_ticketmaster_challenge.ui.search

import androidx.lifecycle.LiveData
import com.example.project_ticketmaster_challenge.common.view_model.LoadStateViewModel
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.model.filter.FilterQueryModel
import com.example.project_ticketmaster_challenge.common.view_model.ViewModelState
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
): LoadStateViewModel<List<EventModel>>(mainDispatcher, ioDispatcher) {

    fun getSearchState(): LiveData<ViewModelState<List<EventModel>>> = getState()

    private var searchJob: Job? = Job()

    fun searchEvents(filterQuery: FilterQueryModel, debounce: Boolean = true) {
        searchJob?.cancel()
        searchJob = loadState(
            delayTime = if (debounce) 500 else 0
        ) {
            searchEventsInteractor.execute(filterQuery)
        }
    }

    override fun isDataEmpty(data: List<EventModel>) = data.isEmpty()
}