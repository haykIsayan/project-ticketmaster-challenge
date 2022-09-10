package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.TestUtils
import com.example.project_ticketmaster_challenge.common.filter.FilterClassificationValues
import com.example.project_ticketmaster_challenge.common.filter.FilterSortValues
import com.example.project_ticketmaster_challenge.data.TicketmasterRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchEventsInteractorTest {

    private lateinit var mockTicketmasterRepository: TicketmasterRepository
    private lateinit var searchEventsInteractor: SearchEventsInteractor

    @Before
    fun setUp() {
        mockTicketmasterRepository = mockk()
        searchEventsInteractor = SearchEventsInteractor(mockTicketmasterRepository)
    }

    @Test
    fun `correctly translates applied filter values and keyword to repository params`() {
        runBlocking {
            val keyword = "keyword"
            val filterQuery = TestUtils.mockFilterQuery.copy(keyword = keyword)
            coEvery { mockTicketmasterRepository.getDiscoveryEvents(
                keyword = keyword,
                classificationId = FilterClassificationValues.sportsClassificationValue,
                sort = FilterSortValues.alphabeticalSortValue,
                countryCode = "US"
            ) }.returns(TestUtils.mockEvents)

            val events = searchEventsInteractor.execute(filterQuery)
            Assert.assertEquals(events, TestUtils.mockEvents)
        }
    }
}