package com.themovieviewer.core.model.data.network

data class PeopleExternalIdsResponse (
    val imdb_id: String?,
    val facebook_id: String?,
    val freebase_mid: String?,
    val freebase_id: String?,
    val tvrage_id: Int?,
    val twitter_id: String?,
    val id: Int,
    val instagram_id: String?
)