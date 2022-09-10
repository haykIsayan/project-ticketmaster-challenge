package com.example.project_ticketmaster_challenge

import com.example.project_ticketmaster_challenge.common.filter.FilterClassificationValues
import com.example.project_ticketmaster_challenge.common.filter.FilterSortValues
import com.example.project_ticketmaster_challenge.model.event.EventModel
import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import com.example.project_ticketmaster_challenge.model.filter.FilterQueryModel
import com.example.project_ticketmaster_challenge.model.filter.FilterType

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
        classificationFilters = listOf(
            FilterModel(
                type = FilterType.classification,
                value = FilterClassificationValues.sportsClassificationValue,
                name = "Sports",
                isApplied = true
            ),
            FilterModel(
                type = FilterType.classification,
                value = FilterClassificationValues.musicClassificationValue,
                name = "Music"
            ),
        ),
        sortFilters = listOf(
            FilterModel(
                type = FilterType.sort,
                value = FilterSortValues.alphabeticalSortValue,
                name = "Alphabetical",
                isApplied = true
            ),
            FilterModel(
                type = FilterType.sort,
                value = FilterSortValues.latestSortValue,
                name = "Latest"
            )
        ),
        countryCodeFilters = listOf(
            FilterModel(
                type = FilterType.countryCode,
                value = "US",
                name = "United States of America",
                isApplied = true
            ),
            FilterModel(
                type = FilterType.countryCode,
                value = "AU",
                name = "Australia"
            )
        )
    )
}