package com.themovieviewer.network.model

data class MovieDetailsResponse(
    val adult: Boolean,
    val backdrop_path: String?,
    val budget: Int,
    val genres: List<MovieGenresDto>,
    val homepage: String?,
    val id: Int,
    val imdb_id: String?,
    val original_language: String,
    val original_title: String,
    val overview: String?,
    val popularity: Float?,
    val poster_path: String?,
    val production_companies: List<MovieProductionCompaniesDto>,
    val production_countries: List<MovieProductionCountriesDto>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int?,
    val spoken_languages: List<MovieSpokenLanguagesDto>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)
