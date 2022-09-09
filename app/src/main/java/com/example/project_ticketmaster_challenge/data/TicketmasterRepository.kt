package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.model.EventModel
import com.example.project_ticketmaster_challenge.model.EventQueryModel
import com.example.project_ticketmaster_challenge.model.FilterModel

interface TicketmasterRepository {
    suspend fun getDiscoveryEvents(eventQuery: EventQueryModel): List<EventModel>

    fun getEventFilters(): List<FilterModel>
}