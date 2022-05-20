package com.themovieviewer.network.model

import com.themovieviewer.core.model.data.CastCrew
import com.themovieviewer.core.model.util.DomainMapper

class CastCrewDtoMapper : DomainMapper<PeopleMovieCreditsCastDto, CastCrew> {

    override fun mapFromDomainModel(domainModel: CastCrew): PeopleMovieCreditsCastDto {
        return PeopleMovieCreditsCastDto(
            character = domainModel.character,
            credit_id = domainModel.credit_id,
            release_date = domainModel.release_date,
            vote_count = domainModel.vote_count,
            video = domainModel.video,
            adult = domainModel.adult,
            vote_average = domainModel.vote_average,
            title = domainModel.title,
            genre_ids = domainModel.genre_ids,
            original_language = domainModel.original_language,
            original_title = domainModel.original_title,
            popularity = domainModel.popularity,
            id = domainModel.id,
            backdrop_path = domainModel.backdrop_path,
            overview = domainModel.overview,
            poster_path = domainModel.poster_path,
        )
    }

    override fun mapToDomainModel(model: PeopleMovieCreditsCastDto): CastCrew {
        return CastCrew(
            character = model.character,
            credit_id = model.credit_id,
            release_date = model.release_date,
            vote_count = model.vote_count,
            video = model.video,
            adult = model.adult,
            vote_average = model.vote_average,
            title = model.title,
            genre_ids = model.genre_ids,
            original_language = model.original_language,
            original_title = model.original_title,
            popularity = model.popularity,
            id = model.id,
            backdrop_path = model.backdrop_path,
            overview = model.overview,
            poster_path = model.poster_path,
        )
    }

    fun toDomainList(initial: List<PeopleMovieCreditsCastDto>): List<CastCrew> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<CastCrew>): List<PeopleMovieCreditsCastDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}