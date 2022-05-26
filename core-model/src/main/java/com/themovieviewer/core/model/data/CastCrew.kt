package com.themovieviewer.core.model.data

data class CastCrew(
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
    val poster_path: String?,
    //crew
    val department: String = "",
    val job: String = "",
)