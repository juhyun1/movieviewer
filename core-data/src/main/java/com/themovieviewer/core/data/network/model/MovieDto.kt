package com.themovieviewer.core.data.network.model

data class MovieDto(
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_title: String,
    val original_language: String?,
    val title: String?,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float


) {
    fun compareTo(dto: MovieDto): Int {
        return if (this.release_date == null || this.release_date.isEmpty()) {
            -1
        } else if (dto.release_date == null || dto.release_date.isEmpty()) {
            1
        } else {
            val b = dto.release_date.substringBefore("-", dto.release_date).toInt()
            release_date.substringBefore("-", release_date).toInt().compareTo(b) * -1
        }
    }
}
