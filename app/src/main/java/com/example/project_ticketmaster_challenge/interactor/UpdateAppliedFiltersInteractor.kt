package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.model.filter.FilterModel

class UpdateAppliedFiltersInteractor {
    fun execute(filters: List<FilterModel>, filter: FilterModel): List<FilterModel> {
        val filterType = filter.type
        val updatedFilters = filters.map {
            if (it.type != filterType) return@map it
            return@map when (it.value) {
                filter.value -> filter
                else -> it.copy(isApplied = false)
            }
        }
        return updatedFilters
    }
}
