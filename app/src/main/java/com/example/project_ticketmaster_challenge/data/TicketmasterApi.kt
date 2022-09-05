package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.model.EventResponseModel
import retrofit2.http.GET

interface TicketmasterApi {
    @GET("discovery/v2/events.json")
    suspend fun getDiscoverEvents(): EventResponseModel
}
