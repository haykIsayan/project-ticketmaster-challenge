package com.example.project_ticketmaster_challenge.common.filter

import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import com.example.project_ticketmaster_challenge.model.filter.FilterType

object FiltersUtils {


    val defaultClassificationFilters = listOf(
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
        FilterModel(
            type = FilterType.classification,
            value = FilterClassificationValues.artsAndTheatreClassificationValue,
            name = "Arts and Theatre"
        ),
        FilterModel(
            type = FilterType.classification,
            value = FilterClassificationValues.filmClassificationValue,
            name = "Film"
        )
    )

    val defaultSortFilters = listOf(
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
        ),
        FilterModel(
            type = FilterType.sort,
            value = FilterSortValues.mostRelevantValue,
            name = "Most Relevant"
        ),
        FilterModel(
            type = FilterType.sort,
            value = FilterSortValues.random,
            name = "Random"
        )
    )

    val defaultCountryCodeFilters = listOf(
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
        ),
        FilterModel(
            type = FilterType.countryCode,
            value = "CA",
            name = "Canada"
        ),
        FilterModel(
            type = FilterType.countryCode,
            value = "CZ",
            name = "Czech Republic"
        )
    )
}
