package com.reactions.fixt.domain.usecase.google

import io.reactivex.Single
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.domain.usecase.BaseUseCase

interface GetFixturesUseCase : BaseUseCase {

    fun getFixtures(): Single<List<Entity.Fixture>>

}