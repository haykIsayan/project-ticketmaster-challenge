package com.example.project_ticketmaster_challenge.model.filter

data class FilterModel(
    val type: FilterType,
    val value: String,
    val name: String,
    val isApplied: Boolean = false
)
