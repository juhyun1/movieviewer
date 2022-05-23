package com.themovieviewer.core.model.data.network

import com.themovieviewer.core.data.network.model.MovieGenresDto
import com.themovieviewer.core.data.network.model.MovieProductionCompaniesDto
import com.themovieviewer.core.data.network.model.MovieProductionCountriesDto
import com.themovieviewer.core.data.network.model.MovieSpokenLanguagesDto

data class MovieDetailsResponse(
    val adult: Boolean = false,
    val backdrop_path: String? = null,
    val budget: Int = 0,
    val genres: List<MovieGenresDto> = emptyList(),
    val homepage: String? = null,
    val id: Int = 0,
    val imdb_id: String? = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String? = null,
    val popularity: Float? = null,
    val poster_path: String? = "",
    val production_companies: List<MovieProductionCompaniesDto> = emptyList(),
    val production_countries: List<MovieProductionCountriesDto> = emptyList(),
    val release_date: String = "",
    val revenue: Long = 0,
    val runtime: Int? = null,
    val spoken_languages: List<MovieSpokenLanguagesDto> = emptyList(),
    val status: String = "",
    val tagline: String? = null,
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Float = 0f,
    val vote_count: Int = 0
)
