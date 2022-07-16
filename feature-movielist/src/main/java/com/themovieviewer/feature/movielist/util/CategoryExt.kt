package com.themovieviewer.feature.movielist.util

import androidx.annotation.StringRes
import com.themovieviewer.core.datastore.Category
import com.themovieviewer.feature.movielist.R

@StringRes fun Category.title(): Int = when (this) {
    Category.NowPlaying -> R.string.bottom_sheet_item_now_playing
    Category.TopRate -> R.string.bottom_sheet_item_top_rate
    Category.Upcoming -> R.string.bottom_sheet_item_upcoming
    Category.Popular -> R.string.bottom_sheet_item_popular
}