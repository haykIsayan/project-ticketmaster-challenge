package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.common.FiltersUtils
import com.example.project_ticketmaster_challenge.data.network.TicketmasterApi
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.model.filter.FilterModel

class TicketmasterDataSource(private val api: TicketmasterApi): TicketmasterRepository {

    override suspend fun getDiscoveryEvents(
        keyword: String?,
        classificationId: String?,
        countryCode: String?,
        sort: String?
    ): List<EventModel> {
        val response = api.getDiscoveryEvents(
            keyword = keyword,
            classificationId = classificationId,
            countryCode = countryCode,
            sort = sort
        )
        return response._embedded?.events ?: emptyList()
    }

    override fun getEventFilters(): List<FilterModel> {
        return FiltersUtils.defaultFilters
    }
}