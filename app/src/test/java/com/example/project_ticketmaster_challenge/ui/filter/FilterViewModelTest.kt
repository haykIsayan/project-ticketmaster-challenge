package com.example.project_ticketmaster_challenge.ui.filter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.project_ticketmaster_challenge.TestUtils
import com.example.project_ticketmaster_challenge.common.filter.FilterClassificationValues
import com.example.project_ticketmaster_challenge.interactor.ApplyFilterToFilterQueryInteractor
import com.example.project_ticketmaster_challenge.interactor.GetDefaultFilterQueryInteractor
import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import com.example.project_ticketmaster_challenge.model.filter.FilterType
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FilterViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mockGetDefaultFilterQueryInteractor: GetDefaultFilterQueryInteractor
    private lateinit var mockApplyFilterToFilterQueryInteractor: ApplyFilterToFilterQueryInteractor
    private lateinit var viewModel: FilterViewModel

    @Before
    fun setUp() {
        mockGetDefaultFilterQueryInteractor = mockk()
        mockApplyFilterToFilterQueryInteractor = mockk()

        every { mockGetDefaultFilterQueryInteractor.execute() }.returns(TestUtils.mockFilterQuery)

        viewModel = FilterViewModel(
            mockGetDefaultFilterQueryInteractor,
            mockApplyFilterToFilterQueryInteractor
        )
    }

    @Test
    fun `test update keyword`() {
        val keyword = "keyword"
        viewModel.onKeywordChanged(keyword)
        val filterQuery = viewModel.getFilterQuery().value
        Assert.assertEquals(keyword, filterQuery?.keyword)
    }

    @Test
    fun `test update filter`() {
        val filter = FilterModel(
            type = FilterType.classification,
            value = FilterClassificationValues.sportsClassificationValue,
            name = "Sports"
        )

        every {
            mockApplyFilterToFilterQueryInteractor.execute(
                TestUtils.mockFilterQuery,
                filter
            )
        }.returns(TestUtils.mockFilterQuery)

        viewModel.onFilterSelected(filter)

        verify {
            mockApplyFilterToFilterQueryInteractor.execute(
                TestUtils.mockFilterQuery,
                filter
            )
        }

        val filterQuery = viewModel.getFilterQuery().value
        Assert.assertEquals(filterQuery, TestUtils.mockFilterQuery)
    }

}