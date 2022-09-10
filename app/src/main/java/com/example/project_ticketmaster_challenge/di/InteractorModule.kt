package com.example.project_ticketmaster_challenge.di

import com.example.project_ticketmaster_challenge.data.TicketmasterRepository
import com.example.project_ticketmaster_challenge.interactor.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Provides
    @Singleton
    fun providesApplyFilterToFilterQueryInteractor(): ApplyFilterToFilterQueryInteractor {
        return ApplyFilterToFilterQueryInteractor()
    }

    @Provides
    @Singleton
    fun providesGetDefaultFilterQueryInteractor(
    ): GetDefaultFilterQueryInteractor {
        return GetDefaultFilterQueryInteractor()
    }

    @Provides
    @Singleton
    fun providesSearchEventsInteractor(
        ticketmasterRepository: TicketmasterRepository
    ): SearchEventsInteractor {
        return SearchEventsInteractor(ticketmasterRepository)
    }
}
