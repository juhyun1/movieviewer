package com.themovieviewer.core.data.network.response

data class PeopleDetailsResponse(
    val birthday: String? = "",
    val known_for_department: String = "",
    val deathday: String? = "",
    val id: Int = 0,
    val name: String = "",
    val also_known_as: List<String> = emptyList(),
    val gender: Int = 0,
    val biography: String = "",
    val popularity: Float = 0f,
    val place_of_birth: String? = "",
    val profile_path: String? = "",
    val adult: Boolean = false,
    val imdb_id: String = "",
    val homepage: String? = "",
)
