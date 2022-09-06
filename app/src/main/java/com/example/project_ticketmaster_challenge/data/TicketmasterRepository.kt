package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.model.EventModel

interface TicketmasterRepository {
    suspend fun getDiscoveryEvents(): List<EventModel>

    suspend fun searchDiscoveryEvents(query: String): List<EventModel>
}