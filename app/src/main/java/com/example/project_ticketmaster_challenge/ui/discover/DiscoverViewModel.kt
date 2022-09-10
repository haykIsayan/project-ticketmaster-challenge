package com.example.project_ticketmaster_challenge.ui.discover

import androidx.lifecycle.LiveData
import com.example.project_ticketmaster_challenge.common.view_model.LoadStateViewModel
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.common.view_model.ViewModelState
import com.example.project_ticketmaster_challenge.interactor.SearchEventsInteractor
import com.example.project_ticketmaster_challenge.model.filter.FilterQueryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    @Named("ioDispatcher")
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    @Named("mainDispatcher")
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val searchEventsInteractor: SearchEventsInteractor
) : LoadStateViewModel<List<EventModel>>(mainDispatcher, ioDispatcher) {

    fun getDiscoverState(): LiveData<ViewModelState<List<EventModel>>> = getState()

    fun loadDiscoverEvents() {
       loadState {
           searchEventsInteractor.execute(FilterQueryModel())
       }
    }

    override fun isDataEmpty(data: List<EventModel>) = data.isEmpty()
}