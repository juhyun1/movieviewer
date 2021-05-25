package com.themovieviewer.repository

import com.themovieviewer.network.model.MovieDetailsDto
import com.themovieviewer.network.response.MovieDetailsResponse
import com.themovieviewer.network.response.TopRatedResponse

interface MovieRepository {
    suspend fun getTopRated(language: String, page : Int): TopRatedResponse
    suspend fun getNowPlaying(language: String, page : Int): TopRatedResponse
    suspend fun getMovieDetails(language: String, movie_id : Int): MovieDetailsDto
}