package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.model.event.EventModel

interface TicketmasterRepository {
    suspend fun getDiscoveryEvents(
        keyword: String?,
        classificationId: String?,
        countryCode: String?,
        sort: String?
    ): List<EventModel>
}
