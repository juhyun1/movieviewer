package com.themovieviewer.domain.model

data class Favorites(
    val favoritesId: String,
    val name: String,
    val kind: String,
    val kindId: String,
    val date: String
)