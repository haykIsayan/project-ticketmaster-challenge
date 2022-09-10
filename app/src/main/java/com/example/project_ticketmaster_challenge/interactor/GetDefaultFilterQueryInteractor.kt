package com.example.project_ticketmaster_challenge.interactor

import com.example.project_ticketmaster_challenge.common.filter.FiltersUtils
import com.example.project_ticketmaster_challenge.model.filter.FilterQueryModel

class GetDefaultFilterQueryInteractor {
    fun execute(): FilterQueryModel {
        return FilterQueryModel(
            classificationFilters = FiltersUtils.defaultClassificationFilters,
            sortFilters = FiltersUtils.defaultSortFilters,
            countryCodeFilters = FiltersUtils.defaultCountryCodeFilters
        )
    }
}
