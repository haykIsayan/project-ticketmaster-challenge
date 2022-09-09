package com.example.project_ticketmaster_challenge.common

import com.example.project_ticketmaster_challenge.model.filter.FilterModel
import com.example.project_ticketmaster_challenge.model.filter.FilterType

object FiltersUtils {
    val defaultFilters = listOf(
        FilterModel(
            type = FilterType.classificationSegment,
            value = EventClassificationIds.sportsClassificationId,
            name = "Sports"
        ),
        FilterModel(
            type = FilterType.classificationSegment,
            value = EventClassificationIds.musicClassificationId,
            name = "Music"
        ),
        FilterModel(
            type = FilterType.classificationSegment,
            value = EventClassificationIds.artsAndTheatreClassificationId,
            name = "Arts and Theatre"
        ),
        FilterModel(
            type = FilterType.classificationSegment,
            value = "KZFzniwnSyZfZ7v7nn",
            name = "Film"
        ),
        FilterModel(
            type = FilterType.sort,
            value = "name,asc",
            name = "Alphabetical"
        ),
        FilterModel(
            type = FilterType.sort,
            value = "date,asc",
            name = "Latest"
        ),
        FilterModel(
            type = FilterType.sort,
            value = "relevance,desc",
            name = "Most Relevant"
        )
    )
}
