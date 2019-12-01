package com.reactions.fixt.data.datasource.google

import io.reactivex.Single
import com.reactions.fixt.data.datasource.BaseDataSource
import com.reactions.fixt.domain.entity.Entity

/**
 * Album database data source
 */
interface GoogleApiDataSource : BaseDataSource {

    fun getFixtures(): Single<List<Entity.Fixture>>

    fun getResults(): Single<List<Entity.Results>>

}