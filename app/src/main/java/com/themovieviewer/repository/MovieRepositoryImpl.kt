package com.themovieviewer.repository

import com.themovieviewer.network.MovieService
import com.themovieviewer.network.model.MovieDetailsResponse
import com.themovieviewer.network.response.*

class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {

//    https://developers.themoviedb.org/3/getting-started/introduction
    private val apiKey = "1139b15b6e07c636d6a411506eea3362"

    override suspend fun getTopRated(language: String?, page: Int): TopRatedResponse {
        return movieService.topRated(api_key = apiKey, language = language, page = page)
    }

    override suspend fun getNowPlaying(language: String, page: Int): TopRatedResponse {
        return movieService.nowPlaying(api_key = apiKey, language = language, page = page)
    }

    override suspend fun getPopular(language: String?, page: Int): TopRatedResponse {
        return movieService.popular(api_key = apiKey, language = language, page = page)
    }

    override suspend fun getMovieDetails(language: String, movie_id: Int): MovieDetailsResponse {
        return movieService.getDetails(api_key = apiKey, language = language, movie_id = movie_id)
    }

    override suspend fun getVideos(language: String, movie_id: Int): VideosResponse {
        return movieService.getVideos(api_key = apiKey, language = language, movie_id = movie_id)
    }


    override suspend fun getMovieCredits(language: String, movie_id: Int): MovieCreditsResponse {
        return movieService.getCredits(api_key = apiKey, language = language, movie_id = movie_id)
    }

    override suspend fun getPeopleDetails(language: String, person_id: Int): PeopleDetailsResponse {
        return movieService.getPeopleDetails(api_key = apiKey, language = language, person_id = person_id)
    }

    override suspend fun getPeopleMovieCredits(language: String, person_id: Int): PeopleMovieCreditsResponse {
        return movieService.getPeopleMovieCredits(api_key = apiKey, language = language, person_id = person_id)
    }

    override suspend fun getRecommendations(language: String, page: Int, movieId: Int): TopRatedResponse {
        return movieService.recommendations(api_key = apiKey, language = language, page = page, movie_id = movieId)
    }

    override suspend fun getExternalIds(language: String, personId: Int): PeopleExternalIdsResponse {
        return movieService.getExternalIds(api_key = apiKey, language = language, person_id = personId)
    }
}
