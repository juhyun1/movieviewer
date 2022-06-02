package com.themovieviewer.core.model.repository

import com.themovieviewer.core.model.data.*

interface MovieRepository {
//    suspend fun getTopRated(language: String?, page: Int): TopRatedResponse
    suspend fun getNowPlaying(language: String = "en_US", page: Int): PageData<Movie>
//    suspend fun getPopular(language: String?, page: Int): TopRatedResponse
    suspend fun getMovieDetails(language: String = "en_US", movie_id: Int): MovieDetail
    suspend fun getVideos(language: String = "en_US", movie_id: Int): PageData<Trailer>
    suspend fun getMovieCredits(language: String = "en_US", movie_id: Int): PageData<CreditsCastCrew>
    suspend fun getPeopleDetails(language: String = "en_US", person_id: Int): People
    suspend fun getPeopleMovieCredits(language: String, person_id: Int): PageData<CastCrew>
    suspend fun getRecommendations(language: String, page: Int, movieId: Int): PageData<Movie>
//    suspend fun getExternalIds(language: String, personId: Int): PeopleExternalIdsResponse
}
