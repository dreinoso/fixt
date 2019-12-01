package com.reactions.fixt.domain.usecase.google

import com.reactions.fixt.domain.repository.repositories.GoogleRepository

/**
 * Album use case implementation
 */
class GetResultsUseCaseImpl (
        private val repository: GoogleRepository) : GetResultsUseCase {

    override fun getResults() = repository.getResults()
}