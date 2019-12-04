package com.reactions.fixt.domain.usecase.google

import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.domain.usecase.BaseUseCase

/**
 * Album use case
 */
interface GetAvailableLeaguesUseCase : BaseUseCase {

    fun getAvailableLeagues(fixtures: List<Entity.Fixture>): MutableSet<String>

}