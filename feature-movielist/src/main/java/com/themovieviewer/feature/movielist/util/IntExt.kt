package com.themovieviewer.feature.movielist.util
import com.themovieviewer.core.datastore.Category
import com.themovieviewer.feature.movielist.R

fun Int.category(): Category = when(this) {
    R.string.bottom_sheet_item_now_playing -> Category.NowPlaying
    R.string.bottom_sheet_item_popular -> Category.Popular
    R.string.bottom_sheet_item_upcoming -> Category.Upcoming
    else -> Category.NowPlaying
}