package com.themovieviewer.util

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.themovieviewer.domain.model.Trailer

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String) {

    val base = "https://image.tmdb.org/t/p/w500"
    Glide.with(imageView)
        .load(base + url)
        .into(imageView)
}

@BindingAdapter("itemSelected")
fun itemSelected(view: View, selected: Boolean) {
    if (selected) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }
}

@BindingAdapter("visible")
fun visible(view: View, visible: Boolean) {
    if(visible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}