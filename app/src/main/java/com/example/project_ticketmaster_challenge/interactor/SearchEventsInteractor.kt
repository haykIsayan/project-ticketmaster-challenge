package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.data.TicketmasterRepository
import com.example.project_ticketmaster_challenge.model.EventModel
import com.example.project_ticketmaster_challenge.model.EventQueryModel

class SearchEventsInteractor(private val ticketmasterRepository: TicketmasterRepository) {
    suspend fun execute(eventQuery: EventQueryModel): List<EventModel> {
        return ticketmasterRepository.getDiscoveryEvents(eventQuery)
    }
}
