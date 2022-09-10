package com.example.project_ticketmaster_challenge.model.filter

data class FilterQueryModel(
    val keyword: String? = "",
    val classificationFilters: List<FilterModel> = emptyList(),
    val sortFilters: List<FilterModel> = emptyList(),
    val countryCodeFilters: List<FilterModel> = emptyList()
)
