package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.model.EventQueryModel
import com.example.project_ticketmaster_challenge.model.FilterModel
import com.example.project_ticketmaster_challenge.model.FilterType

class AddFilterToEventQueryInteractor {

    fun execute(eventQuery: EventQueryModel, filter: FilterModel): EventQueryModel {
        val newValue = if (filter.isApplied) filter.value else null
        val newEventQuery: EventQueryModel = when (filter.type) {
            FilterType.classificationSegment -> eventQuery.copy(classificationId = newValue)
            FilterType.sort -> eventQuery.copy(sort = newValue)
            else -> eventQuery
        }
        return  newEventQuery
    }
}
