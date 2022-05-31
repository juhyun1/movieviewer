package com.themovieviewer.core.data.repository

import com.themovieviewer.core.data.network.MovieService
import com.themovieviewer.core.data.network.mapper.toDomain
import com.themovieviewer.core.data.network.mapper.toDomainList
import com.themovieviewer.core.data.network.response.MovieDetailsResponse
import com.themovieviewer.core.data.network.response.TopRatedResponse
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.model.data.MovieDetail
import com.themovieviewer.core.model.data.PageData
import com.themovieviewer.core.model.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
) : MovieRepository {

//    https://developers.themoviedb.org/3/getting-started/introduction
    private val apiKey = "1139b15b6e07c636d6a411506eea3362"

//    override suspend fun getTopRated(language: String?, page: Int): TopRatedResponse {
//        return movieService.topRated(api_key = apiKey, language = language, page = page)
//    }

    override suspend fun getNowPlaying(language: String, page: Int): PageData<Movie> {
        val movieListResponse: TopRatedResponse = movieService.nowPlaying(api_key = apiKey, language = language, page = page)
        return PageData(list = movieListResponse.toDomainList(), pageCount = movieListResponse.total_pages)
    }
//
//    override suspend fun getPopular(language: String?, page: Int): TopRatedResponse {
//        return movieService.popular(api_key = apiKey, language = language, page = page)
//    }
//
    override suspend fun getMovieDetails(language: String, movie_id: Int): MovieDetail {
        val response: MovieDetailsResponse = movieService.getDetails(api_key = apiKey, language = language, movie_id = movie_id)
        return response.toDomain()
    }
//
//    override suspend fun getVideos(language: String, movie_id: Int): VideosResponse {
//        return movieService.getVideos(api_key = apiKey, language = language, movie_id = movie_id)
//    }
//
//
//    override suspend fun getMovieCredits(language: String, movie_id: Int): MovieCreditsResponse {
//        return movieService.getCredits(api_key = apiKey, language = language, movie_id = movie_id)
//    }
//
//    override suspend fun getPeopleDetails(language: String, person_id: Int): PeopleDetailsResponse {
//        return movieService.getPeopleDetails(api_key = apiKey, language = language, person_id = person_id)
//    }
//
//    override suspend fun getPeopleMovieCredits(language: String, person_id: Int): PeopleMovieCreditsResponse {
//        return movieService.getPeopleMovieCredits(api_key = apiKey, language = language, person_id = person_id)
//    }
//
//    override suspend fun getRecommendations(language: String, page: Int, movieId: Int): TopRatedResponse {
//        return movieService.recommendations(api_key = apiKey, language = language, page = page, movie_id = movieId)
//    }
//
//    override suspend fun getExternalIds(language: String, personId: Int): PeopleExternalIdsResponse {
//        return movieService.getExternalIds(api_key = apiKey, language = language, person_id = personId)
//    }
}
