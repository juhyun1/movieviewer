package com.themovieviewer.core.data.network.model

data class VideosDto (
    val id: String,
    val iso_6391_1: String?,
    val iso_3166_1: String?,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String,
) {
    fun compareTo(dto: VideosDto): Int {
        return when {
            this.type.isEmpty() -> {
                1
            }
            dto.type.isEmpty() -> {
                1
            }
            else -> {
                when {
                    type == "Trailer" -> {
                        -1
                    }
                    dto.type == "Trailer" -> {
                        1
                    }
                    else -> {
                        1
                    }
                }
            }
        }
    }
}
