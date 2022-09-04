package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.model.EventModel
import com.example.project_ticketmaster_challenge.model.EventResponseModel
import retrofit2.http.GET


//https://app.ticketmaster.com/discovery/v2/


interface TicketmasterApi {
    @GET("discovery/v2/events.json?apikey=DW0E98NrxUIfDDtNN7ijruVSm60ryFLX")
    suspend fun getDiscoverEvents(): EventResponseModel
}