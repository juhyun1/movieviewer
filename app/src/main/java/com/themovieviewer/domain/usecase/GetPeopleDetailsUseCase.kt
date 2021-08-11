package com.themovieviewer.domain.usecase

import com.themovieviewer.network.response.PeopleDetailsResponse
import com.themovieviewer.repository.MovieRepository

class GetPeopleDetailsUseCase(
    private val movieRepository: MovieRepository,
): UseCase {

    suspend fun execute(personId: Int, language: String): PeopleDetailsResponse {
        // Coroutine that will be canceled when the ViewModel is cleared.
        return movieRepository.getPeopleDetails(
            language = language,
            person_id = personId
        )
    }
}
