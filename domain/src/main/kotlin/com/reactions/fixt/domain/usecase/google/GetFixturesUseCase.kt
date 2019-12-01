package com.reactions.fixt.domain.usecase.google

import androidx.paging.PagedList
import io.reactivex.Flowable
import io.reactivex.Single
import com.reactions.fixt.domain.common.ResultState
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.domain.usecase.BaseUseCase

/**
 * Album use case
 */
interface GetFixturesUseCase : BaseUseCase {

    fun getFixtures(): Single<List<Entity.Fixture>>

}