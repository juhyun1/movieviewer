package com.themovieviewer.core.ui.util


fun String.imagePath(): String {
    val base = "https://image.tmdb.org/t/p/w500"
    return base + this
}