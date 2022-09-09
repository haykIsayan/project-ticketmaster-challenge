package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.model.event.EventQueryModel
import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import retrofit2.http.Query

interface TicketmasterRepository {
    suspend fun getDiscoveryEvents(
        keyword: String?,
        classificationId: String?,
        countryCode: String?,
        sort: String?
    ): List<EventModel>

    fun getEventFilters(): List<FilterModel>
}