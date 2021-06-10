package com.themovieviewer.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.themovieviewer.R

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun ImageView.loadImage(url: String) {

    val base = "https://image.tmdb.org/t/p/w500"

    Glide.with(this)
        .load(base + url)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun ImageView.loadThumbnail(key: String) {

    val url = "https://img.youtube.com/vi/$key/sddefault.jpg"

    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.placeholder)
        .into(this)
}
