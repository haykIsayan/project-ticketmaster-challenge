package com.example.project_ticketmaster_challenge.data.network

import com.example.project_ticketmaster_challenge.model.EventResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TicketmasterApi {
    @GET("discovery/v2/events.json")
    suspend fun getDiscoveryEvents(@Query("keyword") keyword: String?): EventResponseModel
}
