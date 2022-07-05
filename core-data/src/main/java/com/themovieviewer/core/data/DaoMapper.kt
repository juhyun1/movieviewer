package com.themovieviewer.core.data

import com.themovieviewer.core.model.data.vo.FavoritesMovieVo
import com.themovieviewer.core.model.data.Movie
import com.themovieviewer.core.model.util.DomainMapper
import javax.inject.Inject

class DaoMapper @Inject constructor(): DomainMapper<FavoritesMovieVo, Movie> {
    override fun mapToDomainModel(model: FavoritesMovieVo): Movie {
        return Movie(
            poster_path = model.poster_path,
            adult = model.adult,
            overview = model.overview,
            release_date = model.release_date,
            genre_ids = model.genre_ids,
            id = model.id,
            original_title = model.original_title,
            original_language = null,
            title = null,
            backdrop_path = model.backdrop_path,
            popularity = model.popularity,
            vote_count = model.vote_count,
            video = model.video,
            vote_average = model.vote_average
        )
    }

    override fun mapFromDomainModel(domainModel: Movie): FavoritesMovieVo {
        return FavoritesMovieVo(
            poster_path = domainModel.poster_path,
            adult = domainModel.adult,
            overview = domainModel.overview,
            release_date = domainModel.release_date,
            genre_ids = domainModel.genre_ids,
            id = domainModel.id,
            original_title = domainModel.original_title,
            backdrop_path = domainModel.backdrop_path,
            popularity = domainModel.popularity,
            vote_count = domainModel.vote_count,
            video = domainModel.video,
            vote_average = domainModel.vote_average
        )
    }

    fun toDomainList(initial: List<FavoritesMovieVo>): List<Movie> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Movie>): List<FavoritesMovieVo> {
        return initial.map { mapFromDomainModel(it) }
    }
}
