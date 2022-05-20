package com.themovieviewer.core.model.usecase

import com.themovieviewer.core.model.data.People
import com.themovieviewer.core.model.usecase.UseCase
import com.themovieviewer.network.response.PeopleMapper
import com.themovieviewer.repository.MovieRepository

class GetPeopleDetailsUseCase(
    private val movieRepository: MovieRepository,
    private val peopleMapper: PeopleMapper
): UseCase {

    suspend fun execute(personId: Int, language: String): People {
        val response = movieRepository.getPeopleDetails(language = language, person_id = personId)
        return response.let { peopleMapper.mapToDomainModel(it) }
    }
}
