package com.themovieviewer.core.data.network.response

import com.themovieviewer.core.model.data.People
import com.themovieviewer.core.model.util.DomainMapper

class PeopleMapper : DomainMapper<PeopleDetailsResponse, People> {

    override fun mapFromDomainModel(domainModel: People): PeopleDetailsResponse {
        return PeopleDetailsResponse()
    }

    override fun mapToDomainModel(model: PeopleDetailsResponse): People {
        return People(
            profileImage = model.profile_path,
            knownFor = model.known_for_department,
            gender = model.gender,
            birthday = model.birthday,
            placeOfBirths = model.place_of_birth,
            biography = model.biography,
            name = model.name,
        )
    }

    fun toDomainList(initial: List<PeopleDetailsResponse>): List<People> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<People>): List<PeopleDetailsResponse> {
        return initial.map { mapFromDomainModel(it) }
    }
}