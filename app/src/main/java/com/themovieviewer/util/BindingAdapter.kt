package com.themovieviewer.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.themovieviewer.R
import java.text.DecimalFormat

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
    val str = (voteAverage * 10).toInt().toString()
    view.text = "User Score $str%"
}

@BindingAdapter("gender")
fun setGender(view: TextView, gender: Int) {
    when(gender) {
        1 -> view.text = "Woman"
        2 -> view.text  = "Man"
    }
}

@BindingAdapter("runtime")
fun setRuntime(view: TextView, runtime: Int) {
        val temp = runtime.div(60)
        val temp2 = runtime.rem(60)
        val b = StringBuilder()
        b.append(temp)
        b.append("h ")
        b.append(temp2)
        b.append("m")
        view.text = b.toString()
}

@BindingAdapter("revenue")
fun setRevenue(view: TextView, revenue: Long) {
        val format = DecimalFormat("###,###,###,###")
        view.text = "$${format.format(revenue)}"
}

@BindingAdapter("budget")
fun setBudget(view: TextView, budget: Long) {
    val format = DecimalFormat("###,###,###,###")
    view.text = "$${format.format(budget)}"
}







