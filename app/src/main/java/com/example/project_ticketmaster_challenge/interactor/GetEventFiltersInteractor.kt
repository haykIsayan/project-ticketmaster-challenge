package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.data.TicketmasterRepository
import com.example.project_ticketmaster_challenge.model.filter.FilterModel

class GetEventFiltersInteractor(private val ticketmasterRepository: TicketmasterRepository) {
    fun execute(): List<FilterModel> {
        return ticketmasterRepository.getEventFilters()
    }
}
