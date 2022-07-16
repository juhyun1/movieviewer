package com.themovieviewer.feature.movielist.util
import com.themovieviewer.core.datastore.Category
import com.themovieviewer.core.datastore.Language
import com.themovieviewer.feature.movielist.R

fun Int.category(): Category = when(this) {
    R.string.bottom_sheet_item_now_playing -> Category.NowPlaying
    R.string.bottom_sheet_item_popular -> Category.Popular
    R.string.bottom_sheet_item_upcoming -> Category.Upcoming
    R.string.bottom_sheet_item_top_rate -> Category.TopRate
    else -> Category.NowPlaying
}

fun Int.language(): Language = when(this) {
    R.string.bottom_sheet_item_english -> Language.English
    R.string.bottom_sheet_item_korean -> Language.Korean
    else -> Language.English
}