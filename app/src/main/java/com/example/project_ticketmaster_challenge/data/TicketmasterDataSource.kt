package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.data.network.TicketmasterApi
import com.example.project_ticketmaster_challenge.model.event.EventModel

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
}