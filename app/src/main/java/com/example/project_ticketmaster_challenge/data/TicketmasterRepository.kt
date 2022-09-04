package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.model.EventModel

interface TicketmasterRepository {
    suspend fun getDiscoverEvents(): List<EventModel>
}