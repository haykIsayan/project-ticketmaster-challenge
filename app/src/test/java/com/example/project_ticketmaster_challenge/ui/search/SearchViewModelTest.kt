package com.example.project_ticketmaster_challenge.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.project_ticketmaster_challenge.TestUtils
import com.example.project_ticketmaster_challenge.common.ViewModelState
import com.example.project_ticketmaster_challenge.common.ViewModelState.*
import com.example.project_ticketmaster_challenge.interactor.SearchEventsInteractor
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.model.filter.FilterQueryModel
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mockObserver: Observer<ViewModelState<List<EventModel>>>
    private lateinit var mockSearchEventsInteractor: SearchEventsInteractor

    private lateinit var viewModel: SearchViewModel

    private val testDispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        mockObserver = mockk()
        mockSearchEventsInteractor = mockk()
        viewModel = SearchViewModel(
            testDispatcher,
            testDispatcher,
            mockSearchEventsInteractor
        )
        viewModel.getSearchState().observeForever(mockObserver)
        every { mockObserver.onChanged(any()) } just Runs
    }

    @Test
    fun `when search events interactor succeeds, emits pending and idle states`() = runBlocking {
        coEvery {
            mockSearchEventsInteractor.execute(FilterQueryModel())
        }.returns(TestUtils.mockEvents)

        viewModel.searchEvents(FilterQueryModel(), false)

        coVerify { mockSearchEventsInteractor.execute(FilterQueryModel()) }

        verifySequence {
            mockObserver.onChanged(ViewModelStatePending())
            mockObserver.onChanged(ViewModelStateIdle(TestUtils.mockEvents))
        }
    }

    @Test
    fun `when search events interactor fails, emits pending and error states`() = runBlocking {
        coEvery {
            mockSearchEventsInteractor.execute(FilterQueryModel())
        }.throws(Exception())

        viewModel.searchEvents(FilterQueryModel(), false)

        coVerify { mockSearchEventsInteractor.execute(FilterQueryModel()) }

        verifySequence {
            mockObserver.onChanged(ViewModelStatePending())
            mockObserver.onChanged(ViewModelStateError())
        }
    }

    @Test
    fun `when search events interactor returns empty, emits pending and empty states`() = runBlocking {
        coEvery {
            mockSearchEventsInteractor.execute(FilterQueryModel())
        }.returns(emptyList())

        viewModel.searchEvents(FilterQueryModel(), false)

        coVerify { mockSearchEventsInteractor.execute(FilterQueryModel()) }

        verifySequence {
            mockObserver.onChanged(ViewModelStatePending())
            mockObserver.onChanged(ViewModelStateEmpty())
        }
    }
}