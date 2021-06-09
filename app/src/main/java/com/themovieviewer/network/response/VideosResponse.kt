package com.themovieviewer.network.response

import com.themovieviewer.network.model.VideosDto

class VideosResponse (
    val id: Int,
    val results: List<VideosDto>
)