package com.themovieviewer.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.themovieviewer.R

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) {
    if (url.isNullOrEmpty()) {
        return
    }

    val base = "https://image.tmdb.org/t/p/w500"
    Glide.with(imageView)
        .load(base + url)
        .placeholder(R.drawable.placeholder)
        .into(imageView)

//    imageView.clipToOutline = true
}

@BindingAdapter("load_thumbnail")
fun loadThumbnailImage(imageView: ImageView, key: String?) {
    if (key.isNullOrEmpty()) {
        return
    }
    val url = "https://img.youtube.com/vi/$key/sddefault.jpg"

    Glide.with(imageView)
        .load(url)
        .placeholder(R.drawable.placeholder)
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

@BindingAdapter("showPoster")
fun showPoster(view: View, showPoster: Boolean) {
    if(showPoster) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.INVISIBLE
    }
}

@BindingAdapter("release_date")
fun setReleaseDate(view: TextView, releaseDate: String?) {
    try {
        if (releaseDate.isNullOrEmpty()) {
            view.text = "-"
        } else {
            view.text = releaseDate.substring(0, releaseDate.indexOf("-"))
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@BindingAdapter("vote_average")
fun setVoteAverage(view: TextView, voteAverage: Float) {
    val text = (voteAverage * 10).toInt().toString()
    view.text = "$text %"
}






