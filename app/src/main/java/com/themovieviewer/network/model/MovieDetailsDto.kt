package com.themovieviewer.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailsDto(
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
) : Parcelable

@Parcelize
data class MovieSpokenLanguagesDto(
    val iso_639_1: String,
    val name: String
) : Parcelable

@Parcelize
data class MovieProductionCountriesDto(
    val iso_3166_1: String,
    val name: String
) : Parcelable

@Parcelize
data class MovieProductionCompaniesDto(
    val name: String,
    val id: Int,
    val logo_path: String?,
    val origin_country: String
) : Parcelable

@Parcelize
data class MovieGenresDto(
    val id: Int,
    val name: String,
) : Parcelable

data class DatesDto(
    val maximum: String,
    val minimum: String,
)