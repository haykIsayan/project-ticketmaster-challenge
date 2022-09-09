package com.example.project_ticketmaster_challenge.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.common.ViewModelState
import com.example.project_ticketmaster_challenge.common.ViewModelState.*
import com.example.project_ticketmaster_challenge.interactor.SearchEventsInteractor
import com.example.project_ticketmaster_challenge.model.event.EventQueryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val getLocalSportsInteractor: SearchEventsInteractor
) : ViewModel() {

    private val discoverState = MutableLiveData<ViewModelState<List<EventModel>>>()

    fun getDiscoverState(): LiveData<ViewModelState<List<EventModel>>> = discoverState

    init {
        loadDiscoverEvents()
    }

    private fun loadDiscoverEvents() { // todo rewrite with unit test
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    discoverState.value = ViewModelStatePending()
                }
                val events = getLocalSportsInteractor.execute(EventQueryModel())
                withContext(Dispatchers.Main) {
                    discoverState.value = ViewModelStateIdle(events)
                }
            } catch (e: Exception) {
                println(e.message)
                withContext(Dispatchers.Main) {
                    discoverState.value = ViewModelStateError(e.message)
                }
            }
        }
    }
}