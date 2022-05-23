package com.themovieviewer.core.data.network.response

import com.themovieviewer.core.data.network.model.VideosDto

class VideosResponse (
    val id: Int,
    val results: List<VideosDto>
)