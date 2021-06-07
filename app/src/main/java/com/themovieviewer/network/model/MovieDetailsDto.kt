
package com.themovieviewer.network.model

data class MovieSpokenLanguagesDto(
    val iso_639_1: String,
    val name: String
)

data class MovieProductionCountriesDto(
    val iso_3166_1: String,
    val name: String
)

data class MovieProductionCompaniesDto(
    val name: String,
    val id: Int,
    val logo_path: String?,
    val origin_country: String
)

data class MovieGenresDto(
    val id: Int,
    val name: String,
)

data class DatesDto(
    val maximum: String,
    val minimum: String,
)
