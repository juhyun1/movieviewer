package com.themovieviewer.network

import com.themovieviewer.network.response.TopRatedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/top_rated")
    suspend fun topRated(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): TopRatedResponse

    @GET("movie/now_playing")
    suspend fun nowPlaying(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): TopRatedResponse
}

