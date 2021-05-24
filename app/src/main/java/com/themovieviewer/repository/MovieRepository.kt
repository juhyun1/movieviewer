package com.themovieviewer.repository

import com.themovieviewer.network.response.TopRatedResponse

interface MovieRepository {
    suspend fun getTopRated(language: String, page : Int): TopRatedResponse
    suspend fun getNowPlaying(language: String, page : Int): TopRatedResponse
}