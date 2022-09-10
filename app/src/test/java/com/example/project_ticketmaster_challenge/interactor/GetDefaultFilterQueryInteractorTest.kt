package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.common.filter.FiltersUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetDefaultFilterQueryInteractorTest {

    private lateinit var getDefaultFilterQueryInteractor: GetDefaultFilterQueryInteractor

    @Before
    fun setUp() {
        getDefaultFilterQueryInteractor = GetDefaultFilterQueryInteractor()
    }

    @Test
    fun `returns filter query with default classification, sort and country code`() {
        val filterQuery = getDefaultFilterQueryInteractor.execute()
        Assert.assertEquals(
            filterQuery.classificationFilters,
            FiltersUtils.defaultClassificationFilters
        )
        Assert.assertEquals(
            filterQuery.sortFilters,
            FiltersUtils.defaultSortFilters
        )
        Assert.assertEquals(
            filterQuery.countryCodeFilters,
            FiltersUtils.defaultCountryCodeFilters
        )
    }
}