package com.themovieviewer.repository

import com.themovieviewer.momain.model.Movie
import com.themovieviewer.network.MovieService
import com.themovieviewer.network.model.MovieDtoMapper
import com.themovieviewer.network.response.TopRatedResponse

class MovieRepositoryImpl(
    private val movieService: MovieService
): MovieRepository {
    override suspend fun getTopRated(language: String, page : Int): TopRatedResponse {
        val apiKey = "1139b15b6e07c636d6a411506eea3362"
        return movieService.topRated(api_key = apiKey, language = language,page = page)
    }
}