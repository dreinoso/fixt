package com.reactions.fixt.mvvm.di.main

import dagger.Module
import dagger.Provides
import com.reactions.fixt.data.api.GoogleApi
import com.reactions.fixt.data.datasource.google.GoogleApiDataSource
import com.reactions.fixt.data.datasource.google.GoogleApiDataSourceImpl
import com.reactions.fixt.data.repository.google.GoogleRepositoryImpl
import com.reactions.fixt.domain.repository.repositories.GoogleRepository
import com.reactions.fixt.domain.usecase.google.GetFixturesUseCase
import com.reactions.fixt.domain.usecase.google.GetFixturesUseCaseImpl
import com.reactions.fixt.domain.usecase.google.GetResultsUseCase
import com.reactions.fixt.domain.usecase.google.GetResultsUseCaseImpl

@Module
class GoogleMainModule {

    @Provides
    fun provideApiSource(api: GoogleApi): GoogleApiDataSource = GoogleApiDataSourceImpl(api)

    @Provides
    fun provideRepository( apiSource: GoogleApiDataSource):
            GoogleRepository {
        return GoogleRepositoryImpl(apiSource)
    }

    @Provides
    fun provideGetFixturesUseCaseImpl(repository : GoogleRepository): GetFixturesUseCase = GetFixturesUseCaseImpl(repository)

    @Provides
    fun provideGetResultsUseCaseImpl(repository : GoogleRepository): GetResultsUseCase = GetResultsUseCaseImpl(repository)
}