package com.example.project_ticketmaster_challenge.model

data class EventQueryModel(
    val keyword: String? = null,
    val classificationId: String? = null,
    val sort: String? = null,
    val countryCode: String? = null
)
