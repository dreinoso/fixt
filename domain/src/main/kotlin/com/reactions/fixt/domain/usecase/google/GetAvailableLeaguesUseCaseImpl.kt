package com.reactions.fixt.domain.usecase.google

import com.reactions.fixt.domain.entity.Entity

class GetAvailableLeaguesUseCaseImpl() : GetAvailableLeaguesUseCase {

    override fun getAvailableLeagues(fixtures: List<Entity.Fixture>): MutableSet<String> {
        val leagues: MutableSet<String> = mutableSetOf()
        fixtures.forEach {
            leagues.add(it.competitionStage?.competition?.name.toString())
        }
        return leagues
    }
}