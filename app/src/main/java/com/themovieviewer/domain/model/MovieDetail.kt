package com.themovieviewer.domain.model

data class MovieDetail(
    val revenue: Long = 0,
    val budget: Int = 0,
    val originalLanguage: String,
    val status: String,
    val overview: String? = "",
    val tagline: String?,
    val genres: String,
    val voteAverage: Float = 0f,
    val runtime: Int? = 0,
    val releaseDate: String,
    val title: String,
    val backdropImage: String?,
    val posterImage: String?,
    var isTrailer: Boolean = false,
    var showPoster: Boolean = false
)