package com.reactions.fixt.data.datasource.google

import io.reactivex.Single
import com.reactions.fixt.data.api.GoogleApi
import com.reactions.fixt.data.common.extension.applyIoScheduler
import com.reactions.fixt.data.mapper.map
import com.reactions.fixt.domain.entity.Entity

/**
 * Album network data source implementation
 */
class GoogleApiDataSourceImpl
(private val api: GoogleApi) : GoogleApiDataSource {

    override fun getFixtures(): Single<List<Entity.Fixture>> =
            api.getFixtures()
            .applyIoScheduler()
            .map { item -> item.map { it.map() } }

    override fun getResults(): Single<List<Entity.Results>> =
            api.getResults()
            .applyIoScheduler()
            .map { item -> item.map { it.map() } }

}