package com.example.oms.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*

private val LightColors = lightColorScheme()
private val DarkColors = darkColorScheme()

@Composable
fun OmsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}