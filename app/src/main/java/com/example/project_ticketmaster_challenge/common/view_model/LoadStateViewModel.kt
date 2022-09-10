package com.example.project_ticketmaster_challenge.common.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_ticketmaster_challenge.common.view_model.ViewModelState.*
import kotlinx.coroutines.*

abstract class LoadStateViewModel<DataType>(
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val state = MutableLiveData<ViewModelState<DataType>>()
    fun getState(): LiveData<ViewModelState<DataType>> = state

    protected abstract fun isDataEmpty(data: DataType): Boolean

    fun loadState(
        delayTime: Long = 0,
        loadData: suspend () -> DataType
    ) = viewModelScope.launch(mainDispatcher) {
        try {
            state.value = ViewModelStatePending()
            delay(delayTime)
            val events = withContext(ioDispatcher) { loadData() }
            state.value = if (!isDataEmpty(events)) {
                ViewModelStateIdle(events)
            } else {
                ViewModelStateEmpty()
            }
        } catch (e: Exception) {
            println(e.message)
            state.value = ViewModelStateError(e.message)
        }
    }
}