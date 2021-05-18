package com.themovieviewer.repository

import com.themovieviewer.momain.model.Movie
import com.themovieviewer.network.MovieService
import com.themovieviewer.network.model.MovieDtoMapper

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val mapper: MovieDtoMapper,
): MovieRepository {
    override suspend fun getTopRated(language: String, page : Int): List<Movie> {
        val apiKey = "1139b15b6e07c636d6a411506eea3362"
        return mapper.toDomainList(movieService.TopRated(api_key = apiKey, language = language,page = page).results)
    }
}