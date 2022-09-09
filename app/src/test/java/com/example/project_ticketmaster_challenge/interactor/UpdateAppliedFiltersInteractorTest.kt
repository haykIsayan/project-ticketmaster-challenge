package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.common.FiltersCompanion
import org.junit.Assert
import org.junit.Test

class UpdateAppliedFiltersInteractorTest {

    @Test
    fun updatesOnlyOneFilterPerFilterType() {
        val filters = FiltersCompanion.defaultFilters
        val filter = filters.first().let {
            it.copy(isApplied = !it.isApplied)
        }
        val interactor = UpdateAppliedFiltersInteractor()
        val updatedFilters = interactor.execute(filters, filter)


        val updatedFilter = updatedFilters.find { it.value == filter.value  }
        val restOfUpdatedFilters = updatedFilters.filter { it.value != filter.value }

        val isUpdatedFilterApplied = updatedFilter?.isApplied ?: false
        val areRestOfFiltersApplied = restOfUpdatedFilters.map {
            it.isApplied
        }.fold(true) { isApplied1, isApplied2 ->
            isApplied1 && isApplied2
        }

        Assert.assertTrue(isUpdatedFilterApplied)
        Assert.assertFalse(areRestOfFiltersApplied)
    }
}