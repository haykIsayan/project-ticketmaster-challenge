package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.data.TicketmasterRepository
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.model.event.EventQueryModel
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class SearchEventsInteractorTest {

    @Test
    fun queriesEventsFromTicketmasterRepository() = runBlocking {
        val mockTicketmasterRepository = mockk<TicketmasterRepository>()
        val mockEventQuery = EventQueryModel()
        val mockEvents = listOf(
            EventModel(
                "eventId",
                "eventName",
                "eventType",
                emptyList(),
                emptyList(),
                null
            )
        )
        coEvery { mockTicketmasterRepository.getDiscoveryEvents(mockEventQuery) }.returns(mockEvents)

        val searchEventsInteractor = SearchEventsInteractor(mockTicketmasterRepository)
        val events = searchEventsInteractor.execute(mockEventQuery)

        Assert.assertEquals(events, mockEvents)
        coVerify { mockTicketmasterRepository.getDiscoveryEvents(mockEventQuery) }
    }

}