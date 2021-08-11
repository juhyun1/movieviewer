package com.themovieviewer.domain.model

data class People (
    val profileImage: String?,
    val knownFor: String,
    val gender: Int,
    val birthday: String?,
    val placeOfBirths: String?,
    val biography: String,
    val name: String
)