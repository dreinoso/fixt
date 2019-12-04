package com.reactions.fixt.domain.usecase.google

import com.reactions.fixt.domain.repository.repositories.GoogleRepository

class GetFixturesUseCaseImpl (
        private val repository: GoogleRepository) : GetFixturesUseCase {

    override fun getFixtures() = repository.getFixtures()
}