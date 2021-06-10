package com.themovieviewer.network.model

data class VideosDto (
    val id: String,
    val iso_6391_1: String?,
    val iso_3166_1: String?,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String,
)
