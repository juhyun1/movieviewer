package com.themovieviewer.core.data.mapper

import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.model.data.vo.FavoritesMovieVo

fun FavoritesMovieVo.asModel(): Movie {
    return Movie(
        poster_path = this.poster_path,
        adult = this.adult,
        overview = this.overview,
        release_date = this.release_date,
        genre_ids = this.genre_ids,
        id = this.id,
        original_title = this.original_title,
        original_language = null,
        title = null,
        backdrop_path = this.backdrop_path,
        popularity = this.popularity,
        vote_count = this.vote_count,
        video = this.video,
        vote_average = this.vote_average
    )
}

fun Movie.asEntity(): FavoritesMovieVo {
    return FavoritesMovieVo(
        poster_path = this.poster_path,
        adult = this.adult,
        overview = this.overview,
        release_date = this.release_date,
        genre_ids = this.genre_ids,
        id = this.id,
        original_title = this.original_title,
        backdrop_path = this.backdrop_path,
        popularity = this.popularity,
        vote_count = this.vote_count,
        video = this.video,
        vote_average = this.vote_average
    )
}