package com.themovieviewer.core.data.network.model

data class PeopleMovieCreditsCastDto (
    val character: String,
    val credit_id: String,
    val release_date: String?,
    val vote_count: Int,
    val video: Boolean,
    val adult: Boolean,
    val vote_average: Float,
    val title: String,
    val genre_ids: List<Int>,
    val original_language: String,
    val original_title: String,
    val popularity: Float,
    val id: Int,
    val backdrop_path: String?,
    val overview: String,
    val poster_path: String?
) {
    fun compareTo(dto: PeopleMovieCreditsCastDto): Int {
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

data class PeopleMovieCreditsCrewDto (
    val id: Int,
    val department: String,
    val original_language: String,
    val original_title: String,
    val job: String,
    val overview: String,
    val vote_count: Int,
    val video: Boolean,
    val poster_path: String?,
    val backdrop_path: String?,
    val title: String,
    val popularity: Float,
    val genre_ids: List<Int>,
    val vote_average: Float,
    val adult: Boolean,
    val release_date: String,
    val credit_id: String
)