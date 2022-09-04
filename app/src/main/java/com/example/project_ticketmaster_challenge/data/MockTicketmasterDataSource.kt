package com.example.project_ticketmaster_challenge.data

import com.example.project_ticketmaster_challenge.model.EventModel
import com.example.project_ticketmaster_challenge.model.ImageModel

class MockTicketmasterDataSource: TicketmasterRepository {
    override suspend fun getDiscoverEvents(): List<EventModel> {
        return listOf(
            EventModel(
                id = "Z7r9jZ1AdFY73&quot",
                name = "Los Angeles Dodgers vs. San Diego Padres",
                type = "event",
                images = listOf(
                    ImageModel(
                        url = "https://s1.ticketm.net/dam/a/2f4/5f8bca36-3127-4f87-b573-3dd6485642f4_1344231_RECOMENDATION_16_9.jpg",
                    ),
                )
            ),
            EventModel(
                id = "Z7r9jZ1AdFY73&quot",
                name = "Los Angeles Dodgers vs. San Diego Padres",
                type = "event",
                images = listOf(
                    ImageModel(
                        url = "https://s1.ticketm.net/dam/a/2f4/5f8bca36-3127-4f87-b573-3dd6485642f4_1344231_RECOMENDATION_16_9.jpg",
                    ),
                )
            ),
            EventModel(
                id = "Z7r9jZ1AdFY73&quot",
                name = "Los Angeles Dodgers vs. San Diego Padres",
                type = "event",
                images = listOf(
                    ImageModel(
                        url = "https://s1.ticketm.net/dam/a/2f4/5f8bca36-3127-4f87-b573-3dd6485642f4_1344231_RECOMENDATION_16_9.jpg",
                    ),
                )
            ),
            EventModel(
                id = "Z7r9jZ1AdFY73&quot",
                name = "Los Angeles Dodgers vs. San Diego Padres",
                type = "event",
                images = listOf(
                    ImageModel(
                        url = "https://s1.ticketm.net/dam/a/2f4/5f8bca36-3127-4f87-b573-3dd6485642f4_1344231_RECOMENDATION_16_9.jpg",
                    ),
                )
            )
        )
    }

}