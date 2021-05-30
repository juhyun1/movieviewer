package com.themovieviewer.data

import com.themovieviewer.data.vo.FavoritesMovie
import com.themovieviewer.domain.model.Movie
import com.themovieviewer.domain.util.DomainMapper

class DaoMapper : DomainMapper<FavoritesMovie, Movie> {
    override fun mapToDomainModel(model: FavoritesMovie): Movie {
        return Movie(
            poster_path = model.poster_path,
            adult = model.adult,
            overview = model.overview,
            release_date = model.release_date,
            genre_ids = model.genre_ids,
            id = model.id,
            original_title = model.original_title,
            backdrop_path = model.backdrop_path,
            popularity = model.popularity,
            vote_count = model.vote_count,
            video = model.video,
            vote_average = model.vote_average
        )
    }

    override fun mapFromDomainModel(domainModel: Movie): FavoritesMovie {
        return FavoritesMovie(
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

    fun toDomainList(initial: List<FavoritesMovie>): List<Movie>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Movie>): List<FavoritesMovie>{
        return initial.map { mapFromDomainModel(it) }
    }

}