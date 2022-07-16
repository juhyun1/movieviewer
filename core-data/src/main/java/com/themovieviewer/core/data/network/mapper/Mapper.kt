package com.themovieviewer.core.data.network.mapper

import android.text.TextUtils
import com.themovieviewer.core.data.network.model.CreditsCastCrewDto
import com.themovieviewer.core.data.network.model.MovieDto
import com.themovieviewer.core.data.network.model.PeopleMovieCreditsCastDto
import com.themovieviewer.core.data.network.model.VideosDto
import com.themovieviewer.core.data.network.response.*
import com.themovieviewer.core.model.data.*
import timber.log.Timber

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

fun PeopleDetailsResponse.toDomain(): People {
    return People(
        profileImage = this.profile_path,
        knownFor = this.known_for_department,
        gender = this.gender,
        birthday = this.birthday,
        placeOfBirths = this.place_of_birth,
        biography = this.biography,
        name = this.name,
    )
}

fun CreditsCastCrewDto.toDomain(): CreditsCastCrew {
    return CreditsCastCrew(
        adult = this.adult,
        gender = this.gender,
        id = this.id,
        known_for_department = this.known_for_department,
        name = this.name,
        original_name = this.original_name,
        popularity = this.popularity,
        profile_path = this.profile_path,
        cast_id = this.cast_id,
        character = this.character,
        credit_id = this.credit_id,
        order = this.order,
        department = this.department,
        job = this.job,
    )
}

fun MovieCreditsResponse.toDomainList(): List<CreditsCastCrew> {
    val list = mutableListOf<CreditsCastCrew>()
    this.cast.forEach {
        list.add(it.toDomain())
    }
    return list
}

fun VideosDto.toDomain(): Trailer {
    return Trailer(
        id = this.id,
        iso_6391_1 = this.iso_6391_1,
        iso_3166_1 = this.iso_3166_1,
        key = this.key,
        name = this.name,
        site = this.site,
        size = this.size,
        type = this.type
    )
}


fun VideosResponse.toDomainList(): List<Trailer> {
    val list = mutableListOf<Trailer>()
    this.results.forEach {
        list.add(it.toDomain())
    }
    return list
}

fun PeopleMovieCreditsCastDto.toDomain(): CastCrew {
    return CastCrew(
        character = this.character,
        credit_id = this.credit_id,
        release_date = this.release_date,
        vote_count = this.vote_count,
        video = this.video,
        adult = this.adult,
        vote_average = this.vote_average,
        title = this.title,
        genre_ids = this.genre_ids,
        original_language = this.original_language,
        original_title = this.original_title,
        popularity = this.popularity,
        id = this.id,
        backdrop_path = this.backdrop_path,
        overview = this.overview,
        poster_path = this.poster_path,
    )
}


fun PeopleMovieCreditsResponse.toDomainList(): List<CastCrew> {
    val list = mutableListOf<CastCrew>()
    this.cast.forEach {
        list.add(it.toDomain())
    }
    return list
}


fun CastCrew.toMovie(): Movie {
    return Movie(
        id = this.id,
        poster_path = this.poster_path,
        adult = this.adult,
        overview = this.overview,
        release_date = this.release_date,
        genre_ids = this.genre_ids,
        original_title = this.original_title,
        original_language = this.original_language,
        title = this.title,
        backdrop_path = this.backdrop_path,
        popularity = this.popularity,
        vote_count = this.vote_count,
        video = this.video,
        vote_average = this.vote_average,
    )
}