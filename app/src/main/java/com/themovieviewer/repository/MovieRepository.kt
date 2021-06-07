package com.themovieviewer.repository

import com.themovieviewer.network.model.MovieDetailsResponse
import com.themovieviewer.network.response.MovieCreditsResponse
import com.themovieviewer.network.response.PeopleDetailsResponse
import com.themovieviewer.network.response.TopRatedResponse

interface MovieRepository {
    suspend fun getTopRated(language: String?, page: Int): TopRatedResponse
    suspend fun getNowPlaying(language: String, page: Int): TopRatedResponse
    suspend fun getPopular(language: String?, page: Int): TopRatedResponse
    suspend fun getMovieDetails(language: String, movie_id: Int): MovieDetailsResponse
    suspend fun getMovieCredits(language: String, movie_id: Int): MovieCreditsResponse
    suspend fun getPeopleDetails(language: String, person_id: Int): PeopleDetailsResponse
    suspend fun getRecommendations(language: String, page: Int, movieId: Int): TopRatedResponse
}
