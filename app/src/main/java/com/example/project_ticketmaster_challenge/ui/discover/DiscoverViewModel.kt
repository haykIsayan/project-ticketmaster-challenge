package com.example.project_ticketmaster_challenge.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.common.ViewModelState
import com.example.project_ticketmaster_challenge.common.ViewModelState.*
import com.example.project_ticketmaster_challenge.interactor.SearchEventsInteractor
import com.example.project_ticketmaster_challenge.model.filter.FilterQueryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    @Named("ioDispatcher")
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    @Named("mainDispatcher")
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val searchEventsInteractor: SearchEventsInteractor
) : ViewModel() {

    private val discoverState = MutableLiveData<ViewModelState<List<EventModel>>>()

    fun getDiscoverState(): LiveData<ViewModelState<List<EventModel>>> = discoverState

    fun loadDiscoverEvents() {
        viewModelScope.launch(mainDispatcher) {
            try {
                discoverState.value = ViewModelStatePending()
                val events = withContext(ioDispatcher) { searchEventsInteractor.execute(FilterQueryModel()) }
                discoverState.value = ViewModelStateIdle(events)
            } catch (e: Exception) {
                println(e.message)
                discoverState.value = ViewModelStateError(e.message)
            }
        }
    }
}