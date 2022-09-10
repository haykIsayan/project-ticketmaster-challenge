package com.example.project_ticketmaster_challenge.ui.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_ticketmaster_challenge.interactor.ApplyFilterToFilterQueryInteractor
import com.example.project_ticketmaster_challenge.interactor.GetDefaultFilterQueryInteractor
import com.example.project_ticketmaster_challenge.model.filter.FilterQueryModel
import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getDefaultFilterQueryInteractor: GetDefaultFilterQueryInteractor,
    private val applyFilterToFilterQueryInteractor: ApplyFilterToFilterQueryInteractor
): ViewModel() {

    private val filterQuery = MutableLiveData<FilterQueryModel>()
    fun getFilterQuery(): LiveData<FilterQueryModel> = filterQuery

    init {
        loadFilterQuery()
    }

    private fun loadFilterQuery() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val query = getDefaultFilterQueryInteractor.execute()
                withContext(Dispatchers.Main) { filterQuery.value = query }
            } catch (e: Exception) {

            }
        }
    }

    fun onKeywordChanged(keyword: String) {
        filterQuery.value = filterQuery.value?.copy(keyword = keyword)
    }

    fun onFilterSelected(filter: FilterModel) {
        val currentFilterQuery = filterQuery.value ?: return
        filterQuery.value = applyFilterToFilterQueryInteractor.execute(currentFilterQuery, filter)
    }
}