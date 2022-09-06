package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.data.network.TicketmasterApi
import com.example.project_ticketmaster_challenge.model.EventModel

class TicketmasterDataSource(private val api: TicketmasterApi): TicketmasterRepository {

    override suspend fun getDiscoveryEvents(): List<EventModel> {
        val response = api.getDiscoveryEvents(keyword = null)
        return response._embedded?.events ?: emptyList()
    }

    override suspend fun searchDiscoveryEvents(query: String): List<EventModel> {
        val response = api.getDiscoveryEvents(keyword = query)
        return response._embedded?.events ?: emptyList()
    }

}