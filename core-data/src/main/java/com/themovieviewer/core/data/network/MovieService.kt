package com.themovieviewer.core.data.network

import com.themovieviewer.core.data.network.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/top_rated")
    suspend fun topRated(
        @Query("api_key") api_key: String,
        @Query("language") language: String?,
        @Query("page") page: Int
    ): TopRatedResponse

    @GET("movie/now_playing")
    suspend fun nowPlaying(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): TopRatedResponse

    @GET("movie/popular")
    suspend fun popular(
        @Query("api_key") api_key: String,
        @Query("language") language: String?,
        @Query("page") page: Int
    ): TopRatedResponse

    @GET("movie/{movie_id}/recommendations")
    suspend fun recommendations(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): TopRatedResponse

    @GET("movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): MovieDetailsResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): VideosResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): MovieCreditsResponse

    @GET("person/{person_id}")
    suspend fun getPeopleDetails(
        @Path("person_id") person_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): PeopleDetailsResponse

    @GET("person/{person_id}/movie_credits")
    suspend fun getPeopleMovieCredits(
        @Path("person_id") person_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): PeopleMovieCreditsResponse

    @GET("person/{person_id}/external_ids")
    suspend fun getExternalIds(
        @Path("person_id") person_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): PeopleExternalIdsResponse
}
