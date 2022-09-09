package com.example.project_ticketmaster_challenge.di

import android.content.Context
import android.os.Build
import com.example.project_ticketmaster_challenge.data.TicketmasterRepository
import com.example.project_ticketmaster_challenge.interactor.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Provides
    @Singleton
    fun provideLocale(@ApplicationContext context: Context): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            context.resources.configuration.locales.get(0)
        } else{
            context.resources.configuration.locale
        }
    }

    @Provides
    @Singleton
    fun providesAddFilterToEventQueryInteractor(): ApplyFilterToEventQueryInteractor {
        return ApplyFilterToEventQueryInteractor()
    }

    @Provides
    @Singleton
    fun providesGetDefaultEventQueryInteractor(
    ): GetDefaultEventQueryInteractor {
        return GetDefaultEventQueryInteractor()
    }

    @Provides
    @Singleton
    fun providesSearchEventsInteractor(
        ticketmasterRepository: TicketmasterRepository
    ): SearchEventsInteractor {
        return SearchEventsInteractor(ticketmasterRepository)
    }
}
