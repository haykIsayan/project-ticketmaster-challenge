package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.TestUtils
import com.example.project_ticketmaster_challenge.common.filter.FilterClassificationValues
import com.example.project_ticketmaster_challenge.common.filter.FilterSortValues
import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import com.example.project_ticketmaster_challenge.model.filter.FilterType
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApplyFilterToFilterQueryInteractorTest {

    private lateinit var applyFilterToFilterQueryInteractor: ApplyFilterToFilterQueryInteractor

    @Before
    fun setUp() {
        applyFilterToFilterQueryInteractor = ApplyFilterToFilterQueryInteractor()
    }

    @Test
    fun `test apply classification filter`() {
        val filterQuery = TestUtils.mockFilterQuery
        val filter = FilterModel(
            type = FilterType.classification,
            value = FilterClassificationValues.sportsClassificationValue,
            name = "Sports"
        )
        val updatedFilterQuery = applyFilterToFilterQueryInteractor.execute(filterQuery, filter)
        val updatedFilter = updatedFilterQuery.classificationFilters.find {
            it.value == filter.value
        }
        Assert.assertTrue(updatedFilter?.isApplied ?: false)
    }

    @Test
    fun `test apply sort filter`() {
        val filterQuery = TestUtils.mockFilterQuery
        val filter = FilterModel(
            type = FilterType.sort,
            value = FilterSortValues.latestSortValue,
            name = "Latest"
        )
        val updatedFilterQuery = applyFilterToFilterQueryInteractor.execute(filterQuery, filter)
        val updatedFilter = updatedFilterQuery.sortFilters.find {
            it.value == filter.value
        }
        Assert.assertTrue(updatedFilter?.isApplied ?: false)
    }

    @Test
    fun `test apply country code filter`() {
        val filterQuery = TestUtils.mockFilterQuery
        val filter =  FilterModel(
            type = FilterType.countryCode,
            value = "US",
            name = "United States of America"
        )
        val updatedFilterQuery = applyFilterToFilterQueryInteractor.execute(filterQuery, filter)
        val updatedFilter = updatedFilterQuery.countryCodeFilters.find {
            it.value == filter.value
        }
        Assert.assertTrue(updatedFilter?.isApplied ?: false)
    }
}