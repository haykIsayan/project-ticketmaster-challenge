package com.example.project_ticketmaster_challenge.model.event

import com.example.project_ticketmaster_challenge.model.filter.FilterModel

data class EventQueryModel(
    val keyword: String? = "",
    val filters: List<FilterModel> = emptyList()
)
