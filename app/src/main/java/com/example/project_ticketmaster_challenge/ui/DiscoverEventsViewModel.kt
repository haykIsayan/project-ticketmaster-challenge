package com.example.project_ticketmaster_challenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_ticketmaster_challenge.data.TicketmasterRepository
import com.example.project_ticketmaster_challenge.model.EventModel
import com.example.project_ticketmaster_challenge.ui.ViewModelState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DiscoverEventsViewModel @Inject constructor(
    private val ticketmasterRepository: TicketmasterRepository,
) : ViewModel() {

    init {
        loadDiscoverEvents()
    }

    private val _discoverEvents = MutableLiveData<ViewModelState<List<EventModel>>>()

    fun getDiscoverEvents(): LiveData<ViewModelState<List<EventModel>>> = _discoverEvents

    private fun loadDiscoverEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _discoverEvents.value = ViewModelStatePending()
                }
                val events = ticketmasterRepository.getDiscoverEvents()
                withContext(Dispatchers.Main) {
                    _discoverEvents.value = ViewModelStateIdle(events)
                }
            } catch (e: HttpException) {
                println(e.message)
                withContext(Dispatchers.Main) {
                    _discoverEvents.value = ViewModelStateError(e.message)
                }
            }
        }
    }
}