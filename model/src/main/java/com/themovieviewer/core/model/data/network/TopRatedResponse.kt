package com.themovieviewer.core.model.data.network

import com.google.gson.annotations.SerializedName
import com.themovieviewer.core.data.network.model.DatesDto
import com.themovieviewer.core.data.network.model.MovieDto

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
