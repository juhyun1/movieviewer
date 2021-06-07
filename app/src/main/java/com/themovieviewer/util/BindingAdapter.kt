package com.themovieviewer.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

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
