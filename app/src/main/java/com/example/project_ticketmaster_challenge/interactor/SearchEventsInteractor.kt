package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.data.TicketmasterRepository
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.model.event.EventQueryModel
import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import com.example.project_ticketmaster_challenge.model.filter.FilterType

class SearchEventsInteractor(private val ticketmasterRepository: TicketmasterRepository) {

    suspend fun execute(eventQuery: EventQueryModel): List<EventModel> {
        val keyword = eventQuery.keyword
        val filters = eventQuery.filters
        val classificationId = getAppliedFilterValueByType(filters, FilterType.classification)
        val countryCode = getAppliedFilterValueByType(filters, FilterType.countryCode)
        val sort = getAppliedFilterValueByType(filters, FilterType.sort)
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
