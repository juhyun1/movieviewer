package com.themovieviewer.network.response

import com.google.gson.annotations.SerializedName
import com.themovieviewer.network.model.DatesDto
import com.themovieviewer.network.model.MovieDto

data class TopRatedResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieDto>,

    @SerializedName("dates")
    val dates: DatesDto,

    @SerializedName("total_results")
    val total_results: Int,

    @SerializedName("total_pages")
    val total_pages: Int
)