package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.common.FiltersCompanion
import com.example.project_ticketmaster_challenge.data.network.TicketmasterApi
import com.example.project_ticketmaster_challenge.model.EventModel
import com.example.project_ticketmaster_challenge.model.EventQueryModel
import com.example.project_ticketmaster_challenge.model.FilterModel

class TicketmasterDataSource(private val api: TicketmasterApi): TicketmasterRepository {

    override suspend fun getDiscoveryEvents(eventQuery: EventQueryModel): List<EventModel> {
        val response = api.getDiscoveryEvents(
            keyword = eventQuery.keyword,
            classificationId = eventQuery.classificationId,
            countryCode = eventQuery.countryCode,
            sort = eventQuery.sort
        )
        return response._embedded?.events ?: emptyList()
    }

    override fun getEventFilters(): List<FilterModel> {
        return FiltersCompanion.defaultFilters
    }
}