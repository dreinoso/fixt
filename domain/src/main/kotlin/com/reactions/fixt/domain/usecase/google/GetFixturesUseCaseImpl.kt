package com.reactions.fixt.domain.usecase.google

import com.reactions.fixt.domain.repository.repositories.GoogleRepository
import com.reactions.fixt.domain.usecase.google.GetFixturesUseCase

/**
 * Album use case implementation
 */
class GetFixturesUseCaseImpl (
        private val repository: GoogleRepository) : GetFixturesUseCase {

    override fun getFixtures() = repository.getFixtures()
}