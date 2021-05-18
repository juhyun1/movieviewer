package com.themovieviewer.repository

import com.themovieviewer.momain.model.Movie

interface MovieRepository {
    suspend fun getTopRated(language: String, page : Int): List<Movie>
}