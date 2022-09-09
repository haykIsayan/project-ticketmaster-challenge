package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.common.FiltersUtils
import com.example.project_ticketmaster_challenge.model.event.EventQueryModel
import kotlinx.coroutines.delay

class GetDefaultEventQueryInteractor {
    suspend fun execute(): EventQueryModel {
        return EventQueryModel(filters = FiltersUtils.defaultFilters)
    }
}
