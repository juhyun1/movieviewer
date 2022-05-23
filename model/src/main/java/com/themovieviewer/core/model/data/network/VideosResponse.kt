package com.themovieviewer.core.model.data.network

import com.themovieviewer.core.data.network.model.VideosDto

class VideosResponse (
    val id: Int,
    val results: List<VideosDto>
)