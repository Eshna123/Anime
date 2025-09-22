package com.seekho.animeapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors()

@Composable
fun SeekhoTheme(content: @Composable ()->Unit) {
    MaterialTheme(colors = DarkColorPalette, content = content)
}
