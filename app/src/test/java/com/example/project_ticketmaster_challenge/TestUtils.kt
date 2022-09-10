package com.example.project_ticketmaster_challenge

import com.example.project_ticketmaster_challenge.common.filter.FiltersUtils
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.model.filter.FilterQueryModel

object TestUtils {
    val mockEvents = listOf(
        EventModel(
            id = "",
            name = "Phoenix Suns vs. Golden State Warriors",
            type = "",
            images = emptyList(),
            classifications = emptyList(),
            dates = null
        )
    )

    val mockFilterQuery = FilterQueryModel(
        classificationFilters = FiltersUtils.defaultClassificationFilters,
        sortFilters = FiltersUtils.defaultSortFilters,
        countryCodeFilters = FiltersUtils.defaultCountryCodeFilters
    )
}