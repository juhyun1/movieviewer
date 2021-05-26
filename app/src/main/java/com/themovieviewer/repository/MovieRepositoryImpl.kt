package com.themovieviewer.repository

import com.themovieviewer.network.MovieService
import com.themovieviewer.network.model.MovieDetailsResponse
import com.themovieviewer.network.response.MovieCreditsResponse
import com.themovieviewer.network.response.PeopleDetailsResponse
import com.themovieviewer.network.response.TopRatedResponse

class MovieRepositoryImpl(
    private val movieService: MovieService
): MovieRepository {
    private val apiKey = "1139b15b6e07c636d6a411506eea3362"
    override suspend fun getTopRated(language: String, page : Int): TopRatedResponse {
        return movieService.topRated(api_key = apiKey, language = language,page = page)
    }

    override suspend fun getNowPlaying(language: String, page : Int): TopRatedResponse {
        return movieService.nowPlaying(api_key = apiKey, language = language,page = page)
    }

    override suspend fun getMovieDetails(language: String, movie_id : Int): MovieDetailsResponse {
        return movieService.getDetails(api_key = apiKey, language = language,movie_id = movie_id)
    }

    override suspend fun getMovieCredits(language: String, movie_id : Int): MovieCreditsResponse {
        return movieService.getCredits(api_key = apiKey, language = language,movie_id = movie_id)
    }

    override suspend fun getPeopleDetails(language: String, person_id : Int): PeopleDetailsResponse {
        return movieService.getPeopleDetails(api_key = apiKey, language = language,person_id = person_id)
    }
}