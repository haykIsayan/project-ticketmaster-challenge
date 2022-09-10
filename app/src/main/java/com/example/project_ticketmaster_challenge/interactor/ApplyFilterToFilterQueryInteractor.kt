package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.model.filter.FilterQueryModel
import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import com.example.project_ticketmaster_challenge.model.filter.FilterType

class ApplyFilterToFilterQueryInteractor {

    fun execute(filterQuery: FilterQueryModel, filter: FilterModel): FilterQueryModel {
        val updatedFilter = filter.copy(isApplied = !filter.isApplied)
        return when (filter.type) {
            FilterType.classification -> filterQuery.copy(
                classificationFilters = updateFilters(updatedFilter, filterQuery.classificationFilters)
            )
            FilterType.sort -> filterQuery.copy(
                sortFilters = updateFilters(updatedFilter, filterQuery.sortFilters)
            )
            FilterType.countryCode -> filterQuery.copy(
                countryCodeFilters = updateFilters(updatedFilter, filterQuery.countryCodeFilters)
            )
        }
    }

    private fun updateFilters(filter: FilterModel, filters: List<FilterModel>): List<FilterModel> {
        return filters.map {
            return@map when (it.value) {
                filter.value -> filter
                else -> it.copy(isApplied = false)
            }
        }
    }
}
