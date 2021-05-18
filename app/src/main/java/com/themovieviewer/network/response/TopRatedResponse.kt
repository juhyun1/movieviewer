package com.themovieviewer.network.response

import com.google.gson.annotations.SerializedName
import com.themovieviewer.momain.model.Movie

data class TopRatedResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<Movie>,

    @SerializedName("total_results")
    val total_results: Int,

    @SerializedName("total_pages")
    val total_pages: Int
)