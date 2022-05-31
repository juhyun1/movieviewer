package com.themovieviewer.core.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WidthSpacer(width: Float) {
    Spacer(modifier = Modifier.width(width = width.dp).height(height = 1.dp))
}

@Composable
fun HeightSpacer(height: Float) {
    Spacer(modifier = Modifier.height(height = height.dp).width(width = 1.dp))
}