package com.example.project_ticketmaster_challenge.common

import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import com.example.project_ticketmaster_challenge.model.filter.FilterType

object FiltersUtils {
    val defaultFilters = listOf(
        FilterModel(
            type = FilterType.classification,
            value = EventClassificationValues.sportsClassificationValue,
            name = "Sports",
            isApplied = true
        ),
        FilterModel(
            type = FilterType.classification,
            value = EventClassificationValues.musicClassificationValue,
            name = "Music"
        ),
        FilterModel(
            type = FilterType.classification,
            value = EventClassificationValues.artsAndTheatreClassificationValue,
            name = "Arts and Theatre"
        ),
        FilterModel(
            type = FilterType.classification,
            value = EventClassificationValues.filmClassificationValue,
            name = "Film"
        ),
        FilterModel(
            type = FilterType.sort,
            value = EventSortValues.alphabeticalSortValue,
            name = "Alphabetical",
            isApplied = true
        ),
        FilterModel(
            type = FilterType.sort,
            value = EventSortValues.latestSortValue,
            name = "Latest"
        ),
        FilterModel(
            type = FilterType.sort,
            value = EventSortValues.mostRelevantValue,
            name = "Most Relevant"
        )
    )
}
