package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.common.EventClassificationIds
import com.example.project_ticketmaster_challenge.model.EventQueryModel
import com.example.project_ticketmaster_challenge.model.FilterModel
import com.example.project_ticketmaster_challenge.model.FilterType
import org.junit.Assert
import org.junit.Test

class AddFilterToEventQueryInteractorTest {

    @Test
    fun whenAddingFilterWithTypeClassification() {
        val eventQuery = EventQueryModel()
        val filter = FilterModel(
            type = FilterType.classificationSegment,
            value = EventClassificationIds.sportsClassificationId,
            name = "Sports",
            isApplied = true
        )

        val interactor = AddFilterToEventQueryInteractor()
        val newEventQuery = interactor.execute(eventQuery, filter)

        Assert.assertEquals(
            eventQuery.copy(
                classificationId = EventClassificationIds.sportsClassificationId
            ),
            newEventQuery,
        )
    }

    @Test
    fun whenAddingFilterWithTypeSort() {
        val eventQuery = EventQueryModel()
        val filter =  FilterModel(
            type = FilterType.sort,
            value = "name,asc",
            name = "Alphabetical",
            isApplied = true
        )

        val interactor = AddFilterToEventQueryInteractor()
        val newEventQuery = interactor.execute(eventQuery, filter)

        Assert.assertEquals(
            eventQuery.copy(
                sort = "name,asc"
            ),
            newEventQuery,
        )
    }
}
