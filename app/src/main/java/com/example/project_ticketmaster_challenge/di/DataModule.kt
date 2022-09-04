package com.example.project_ticketmaster_challenge.di

import com.example.project_ticketmaster_challenge.data.MockTicketmasterDataSource
import com.example.project_ticketmaster_challenge.data.TicketmasterApi
import com.example.project_ticketmaster_challenge.data.TicketmasterDataSource
import com.example.project_ticketmaster_challenge.data.TicketmasterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideTicketmasterApi(): TicketmasterApi {
        return Retrofit.Builder()
            .baseUrl("https://app.ticketmaster.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TicketmasterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTicketmasterRepository(api: TicketmasterApi): TicketmasterRepository {
        return TicketmasterDataSource(api)
    }
}