package com.example.project_ticketmaster_challenge.di

import android.content.Context
import com.example.project_ticketmaster_challenge.R
import com.example.project_ticketmaster_challenge.data.*
import com.example.project_ticketmaster_challenge.data.network.TicketmasterApi
import com.example.project_ticketmaster_challenge.data.network.TicketmasterClientInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideTicketmasterApi(@ApplicationContext context: Context): TicketmasterApi {
        val apiKey = context.resources.getString(R.string.ticketmaster_api_key)
        val baseUrl = context.resources.getString(R.string.ticketmaster_api_base_url)
        val client = OkHttpClient.Builder()
            .addInterceptor(TicketmasterClientInterceptor(apiKey))
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TicketmasterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTicketmasterRepository(api: TicketmasterApi): TicketmasterRepository {
        return TicketmasterDataSource(api)
    }
}