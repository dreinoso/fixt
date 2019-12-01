package com.reactions.fixt.data.repository.google

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import com.reactions.fixt.data.common.extension.applyIoScheduler
import com.reactions.fixt.data.datasource.album.AlbumsApiDataSource
import com.reactions.fixt.data.datasource.album.AlbumsDatabaseDataSource
import com.reactions.fixt.data.datasource.google.GoogleApiDataSource
import com.reactions.fixt.data.repository.BaseRepositoryImpl
import com.reactions.fixt.data.repository.album.RepoBoundaryCallback
import com.reactions.fixt.domain.common.ResultState
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.domain.repository.repositories.AlbumsRepository
import com.reactions.fixt.domain.repository.repositories.GoogleRepository

/**
 * Album repository implementation
 */
class GoogleRepositoryImpl(
        private val apiSource: GoogleApiDataSource
) : BaseRepositoryImpl<Entity.Fixture>(), GoogleRepository {

    override fun getFixtures(): Single<List<Entity.Fixture>> {
        return apiSource.getFixtures()
    }

    override fun getResults(): Single<List<Entity.Results>> {
        return apiSource.getResults()
    }
}