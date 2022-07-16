package com.themovieviewer.core.model.data

data class PageData<T>(
    val list: List<T>,
    val pageCount: Int
)