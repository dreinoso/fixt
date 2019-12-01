package com.reactions.fixt.domain.repository.repositories

import androidx.paging.PagedList
import io.reactivex.Flowable
import com.reactions.fixt.domain.common.ResultState
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.domain.repository.BaseRepository
import io.reactivex.Single

interface GoogleRepository : BaseRepository {

    fun getFixtures(): Single<List<Entity.Fixture>>

    fun getResults(): Single<List<Entity.Results>>

}