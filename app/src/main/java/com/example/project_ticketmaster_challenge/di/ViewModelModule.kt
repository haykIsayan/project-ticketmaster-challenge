package com.example.project_ticketmaster_challenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    @Named("ioDispatcher")
    fun provideLoadStateDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named("mainDispatcher")
    fun provideEmitStateDispatcher(): CoroutineDispatcher = Dispatchers.Main
}