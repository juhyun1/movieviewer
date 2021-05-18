package com.themovieviewer.repository

interface MovieRepository {
    suspend fun getTopRated(language: String, region : String)
}