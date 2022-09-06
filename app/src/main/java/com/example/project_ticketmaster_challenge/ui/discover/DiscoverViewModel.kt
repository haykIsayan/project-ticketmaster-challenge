package com.example.project_ticketmaster_challenge.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_ticketmaster_challenge.data.TicketmasterRepository
import com.example.project_ticketmaster_challenge.model.EventModel
import com.example.project_ticketmaster_challenge.ui.ViewModelState
import com.example.project_ticketmaster_challenge.ui.ViewModelState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val ticketmasterRepository: TicketmasterRepository,
) : ViewModel() {

    private val discoverState = MutableLiveData<ViewModelState<List<EventModel>>>()

    fun getDiscoverState(): LiveData<ViewModelState<List<EventModel>>> = discoverState

    init {
        loadDiscoverEvents()
    }

    private fun loadDiscoverEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    discoverState.value = ViewModelStatePending()
                }
                val events = ticketmasterRepository.getDiscoveryEvents()
                withContext(Dispatchers.Main) {
                    discoverState.value = ViewModelStateIdle(events)
                }
            } catch (e: HttpException) {
                println(e.message)
                withContext(Dispatchers.Main) {
                    discoverState.value = ViewModelStateError(e.message)
                }
            }
        }
    }
}