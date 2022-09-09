package com.example.project_ticketmaster_challenge.model

data class FilterModel(
    val type: FilterType,
    val value: String,
    val name: String,
    val isApplied: Boolean = false
)
