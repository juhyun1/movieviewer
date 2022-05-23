package com.themovieviewer.core.model.data.network

import android.text.TextUtils
import com.themovieviewer.core.model.data.MovieDetail
import com.themovieviewer.core.model.util.DomainMapper

class MovieDetailMapper: DomainMapper<MovieDetailsResponse, MovieDetail> {

    override fun mapFromDomainModel(domainModel: MovieDetail): MovieDetailsResponse {
        return MovieDetailsResponse()
    }

    override fun mapToDomainModel(model: MovieDetailsResponse): MovieDetail {
        val list = ArrayList<String>()
        for (genres in model.genres) {
            list.add(genres.name)
        }
        val genres = TextUtils.join(",", list)

        return MovieDetail(
            revenue = model.revenue,
            budget = model.budget,
            originalLanguage = model.original_language,
            status = model.status,
            overview = model.overview,
            tagline = model.tagline,
            genres = genres,
            voteAverage = model.vote_average,
            runtime = model.runtime,
            releaseDate = model.release_date,
            title = model.title,
            backdropImage = model.backdrop_path,
            posterImage = model.poster_path,
        )
    }

    fun toDomainList(initial: List<MovieDetailsResponse>): List<MovieDetail> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<MovieDetail>): List<MovieDetailsResponse> {
        return initial.map { mapFromDomainModel(it) }
    }
}