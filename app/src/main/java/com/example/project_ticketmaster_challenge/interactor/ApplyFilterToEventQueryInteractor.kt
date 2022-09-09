package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.model.event.EventQueryModel
import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import com.example.project_ticketmaster_challenge.model.filter.FilterType

class ApplyFilterToEventQueryInteractor {

    fun execute(eventQuery: EventQueryModel, filter: FilterModel): EventQueryModel {
        val updatedFilter = filter.copy(isApplied = !filter.isApplied)
        val filters = eventQuery.filters
        val filterType = filter.type
        val updatedFilters = filters.map {
            if (it.type != filterType) return@map it
            return@map when (it.value) {
                filter.value -> updatedFilter
                else -> it.copy(isApplied = false)
            }
        }
        return  eventQuery.copy(filters = updatedFilters)
    }
}
