package com.themovieviewer.network

import com.themovieviewer.network.response.TopRatedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("search")
    suspend fun TopRated(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): TopRatedResponse
}