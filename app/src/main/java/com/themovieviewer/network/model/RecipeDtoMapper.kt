package com.themovieviewer.network.model

import com.themovieviewer.momain.model.Movie
import com.themovieviewer.momain.util.DomainMapper

class RecipeDtoMapper : DomainMapper<MovieDto, Movie> {

    override fun mapToDomainModel(model: MovieDto): Movie {
        return Movie(
        //TODO
        )
    }

    override fun mapFromDomainModel(domainModel: Movie): MovieDto {
        return MovieDto(
        //TODO
        )
    }
}