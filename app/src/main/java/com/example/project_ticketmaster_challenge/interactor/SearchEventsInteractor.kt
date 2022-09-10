package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.data.TicketmasterRepository
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.model.filter.FilterQueryModel
import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import com.example.project_ticketmaster_challenge.model.filter.FilterType

class SearchEventsInteractor(private val ticketmasterRepository: TicketmasterRepository) {

    suspend fun execute(filterQuery: FilterQueryModel): List<EventModel> {
        val keyword = filterQuery.keyword
        val classificationId = getAppliedFilterValueByType(filterQuery.classificationFilters, FilterType.classification)
        val countryCode = getAppliedFilterValueByType(filterQuery.countryCodeFilters, FilterType.countryCode)
        val sort = getAppliedFilterValueByType(filterQuery.sortFilters, FilterType.sort)
        return ticketmasterRepository.getDiscoveryEvents(
            keyword = keyword,
            classificationId = classificationId,
            countryCode = countryCode,
            sort = sort
        )
    }

    private fun getAppliedFilterValueByType(
        filters: List<FilterModel>,
        type: FilterType
    ) = filters.find { it.type == type && it.isApplied }?.value
}
