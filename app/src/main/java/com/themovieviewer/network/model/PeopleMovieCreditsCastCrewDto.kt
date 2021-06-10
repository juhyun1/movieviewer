package com.themovieviewer.network.model

data class PeopleMovieCreditsCastDto (
    val character: String,
    val credit_id: String,
    val release_date: String?,
    val vote_count: Int,
    val video: Boolean,
    val adult: Boolean,
    val vote_average: Float,
    val title: String,
    val genre_ids: List<Int>,
    val original_language: String,
    val original_title: String,
    val popularity: Float,
    val id: Int,
    val backdrop_path: String?,
    val overview: String,
    val poster_path: String?
)

data class PeopleMovieCreditsCrewDto (
    val id: Int,
    val department: String,
    val original_language: String,
    val original_title: String,
    val job: String,
    val overview: String,
    val vote_count: Int,
    val video: Boolean,
    val poster_path: String?,
    val backdrop_path: String?,
    val title: String,
    val popularity: Float,
    val genre_ids: List<Int>,
    val vote_average: Float,
    val adult: Boolean,
    val release_date: String,
    val credit_id: String
)