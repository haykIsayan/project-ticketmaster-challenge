package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.model.EventModel

class TicketmasterDataSource(private val api: TicketmasterApi): TicketmasterRepository {
    override suspend fun getDiscoverEvents(): List<EventModel> {
        val response = api.getDiscoverEvents()
        return response._embedded?.events ?: emptyList()
    }
}