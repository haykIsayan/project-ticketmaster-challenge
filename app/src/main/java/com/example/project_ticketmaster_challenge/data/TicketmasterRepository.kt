package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.model.event.EventQueryModel
import com.example.project_ticketmaster_challenge.model.filter.FilterModel

interface TicketmasterRepository {
    suspend fun getDiscoveryEvents(eventQuery: EventQueryModel): List<EventModel>

    fun getEventFilters(): List<FilterModel>
}