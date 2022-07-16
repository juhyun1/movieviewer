package com.themovieviewer.core.ui.util

fun Float.score(): String {
    val str = (this * 10).toInt().toString()
    return "$str%"
}

