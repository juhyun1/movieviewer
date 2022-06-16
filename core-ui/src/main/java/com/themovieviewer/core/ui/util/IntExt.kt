package com.themovieviewer.core.ui.util

import java.text.DecimalFormat

fun Int.currency(): String {
    val format = DecimalFormat("#,###")
    return "$${format.format(this)}"
}