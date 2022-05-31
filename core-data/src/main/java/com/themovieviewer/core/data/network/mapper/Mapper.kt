package com.themovieviewer.core.data.network.mapper

import android.text.TextUtils
import com.themovieviewer.core.data.network.model.MovieDto
import com.themovieviewer.core.data.network.response.MovieDetailsResponse
import com.themovieviewer.core.data.network.response.TopRatedResponse
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.model.data.MovieDetail

fun MovieDto.toDomain(): Movie {
    return Movie(
        poster_path = this.poster_path,
        adult = this.adult,
        overview = this.overview,
        release_date = this.release_date,
        genre_ids = this.genre_ids,
        id = this.id,
        original_title = this.original_title,
        original_language = this.original_language,
        title = this.title,
        backdrop_path = this.backdrop_path,
        popularity = this.popularity,
        vote_count = this.vote_count,
        video = this.video,
        vote_average = this.vote_average
    )
}

fun TopRatedResponse.toDomainList(): List<Movie> {
    val list = mutableListOf<Movie>()
    this.results.forEach {
        list.add(it.toDomain())
    }
    return list
}

fun MovieDetailsResponse.toDomain(): MovieDetail {
    val list = ArrayList<String>()
    for (genres in this.genres) {
        list.add(genres.name)
    }
    val genres = TextUtils.join(",", list)

    return MovieDetail(
        revenue = this.revenue,
        budget = this.budget,
        originalLanguage = this.original_language,
        status = this.status,
        overview = this.overview,
        tagline = this.tagline,
        genres = genres,
        voteAverage = this.vote_average,
        runtime = this.runtime,
        releaseDate = this.release_date,
        title = this.title,
        backdropImage = this.backdrop_path,
        posterImage = this.poster_path,
    )
}
