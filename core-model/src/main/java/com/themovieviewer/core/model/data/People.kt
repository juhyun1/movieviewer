package com.themovieviewer.core.model.data

data class People (
    val profileImage: String?,
    val knownFor: String,
    val gender: Int,
    val birthday: String?,
    val placeOfBirths: String?,
    val biography: String,
    val name: String
)