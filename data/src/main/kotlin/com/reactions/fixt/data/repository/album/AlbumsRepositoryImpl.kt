package com.reactions.fixt.data.repository.album

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import com.reactions.fixt.data.common.extension.applyIoScheduler
import com.reactions.fixt.data.datasource.album.AlbumsApiDataSource
import com.reactions.fixt.data.datasource.album.AlbumsDatabaseDataSource
import com.reactions.fixt.data.repository.BaseRepositoryImpl
import com.reactions.fixt.domain.common.ResultState
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.domain.repository.album.AlbumsRepository

/**
 * Album repository implementation
 */
class AlbumsRepositoryImpl(
        private val apiSource: AlbumsApiDataSource,
        private val databaseSource: AlbumsDatabaseDataSource
) : BaseRepositoryImpl<Entity.Album>(), AlbumsRepository {

    /**
     * Perform implementation
     */
    override fun getAlbums(): Flowable<ResultState<PagedList<Entity.Album>>> {
        val dataSourceFactory = databaseSource.getAlbums()
        val boundaryCallback = RepoBoundaryCallback(apiSource, databaseSource)
        val data = RxPagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .buildFlowable(BackpressureStrategy.BUFFER)

        return data
                .applyIoScheduler()
                .map { d ->
                    if (d.size > 0)
                        ResultState.Success(d) as ResultState<PagedList<Entity.Album>>
                    else
                        ResultState.Loading(d) as ResultState<PagedList<Entity.Album>>
                }
                .onErrorReturn { e -> ResultState.Error(e, null) }
    }

    override fun deleteAlbum(album: Entity.Album): Single<ResultState<Int>> =
            databaseSource.deleteAlbum(album).map {
                ResultState.Success(it) as ResultState<Int>
            }.onErrorReturn {
                ResultState.Error(it, null)
            }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}