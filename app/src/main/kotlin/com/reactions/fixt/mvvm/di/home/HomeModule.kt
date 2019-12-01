package com.reactions.fixt.mvvm.di.home

import dagger.Module
import dagger.Provides
import com.com.reactions.fixt.data.api.AlbumApi
import com.reactions.fixt.data.datasource.album.AlbumsApiDataSource
import com.reactions.fixt.data.datasource.album.AlbumsApiDataSourceImpl
import com.reactions.fixt.data.datasource.album.AlbumsDatabaseDataSource
import com.reactions.fixt.data.datasource.album.AlbumsDatabaseDataSourceImpl
import com.reactions.fixt.data.db.album.AlbumDao
import com.reactions.fixt.data.repository.album.AlbumsRepositoryImpl
import com.reactions.fixt.domain.repository.repositories.AlbumsRepository
import com.reactions.fixt.domain.usecase.album.GetAlbumsUseCase
import com.reactions.fixt.domain.usecase.album.GetAlbumsUseCaseImpl
import com.reactions.fixt.presentation.common.transformer.AsyncFTransformer
import com.reactions.fixt.presentation.common.transformer.AsyncSTransformer
import java.util.concurrent.Executors

@Module
class HomeModule {

    @Provides
    //@PerFragment
    fun provideDatabaseSource(albumDao: AlbumDao): AlbumsDatabaseDataSource =
            AlbumsDatabaseDataSourceImpl(albumDao, Executors.newSingleThreadExecutor())

    @Provides
    //@PerFragment
    fun provideApiSource(api: AlbumApi): AlbumsApiDataSource = AlbumsApiDataSourceImpl(api)

    @Provides
    //@PerFragment
    fun provideRepository(
            apiSource: AlbumsApiDataSource,
            databaseSource: AlbumsDatabaseDataSource
    ): AlbumsRepository {
        return AlbumsRepositoryImpl(apiSource, databaseSource)
    }

    @Provides
    //@PerFragment
    fun provideGetAlbumsUseCaseImpl(repository: AlbumsRepository): GetAlbumsUseCase = GetAlbumsUseCaseImpl(AsyncFTransformer(), AsyncSTransformer(), AsyncSTransformer(), repository)
}
