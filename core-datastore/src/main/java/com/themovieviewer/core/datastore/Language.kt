package com.themovieviewer.core.datastore

enum class Language {
    English, Korean;

    fun getLocale(): String = when(this) {
        English -> "en_US"
        Korean -> "ko-KR"
    }
}