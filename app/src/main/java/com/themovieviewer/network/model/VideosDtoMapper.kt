package com.themovieviewer.network.model

import com.themovieviewer.core.model.data.Trailer
import com.themovieviewer.core.model.util.DomainMapper

class VideosDtoMapper : DomainMapper<VideosDto, Trailer> {

    override fun mapFromDomainModel(domainModel: Trailer): VideosDto {
        return VideosDto(
            id = domainModel.id,
            iso_6391_1 = domainModel.iso_6391_1,
            iso_3166_1 = domainModel.iso_3166_1,
            key = domainModel.key,
            name = domainModel.name,
            site = domainModel.site,
            size = domainModel.size,
            type = domainModel.type
        )
    }

    override fun mapToDomainModel(model: VideosDto): Trailer {
        return Trailer(
            id = model.id,
            iso_6391_1 = model.iso_6391_1,
            iso_3166_1 = model.iso_3166_1,
            key = model.key,
            name = model.name,
            site = model.site,
            size = model.size,
            type = model.type
        )
    }

    fun toDomainList(initial: List<VideosDto>): List<Trailer> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Trailer>): List<VideosDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}