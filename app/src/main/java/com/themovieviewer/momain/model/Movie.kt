package com.themovieviewer.momain.model

data class Movie (
    val poster_path: String,
    val adult: Boolean,
    val overview: String,
    val release_date: String,
    val genre_ids: String,
    val id: Int,
    val original_title: String,
    val backdrop_path: String?,
    val popularity: Int,
    val vote_count: Int,
    val video: Boolean,
    val video_average: Int,
)