package com.example.pbmpraktikum.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Light Theme
private val LightColorScheme = lightColorScheme(
    background = BackgroundBlueLight,
    surface = SurfaceBlueLight,
    onSurface = OnSurfaceBlueLight,
    secondary = SecondaryBlueLight
)

// Dark Theme
private val DarkColorScheme = darkColorScheme(
    background = BackgroundBlueDark,
    surface = SurfaceBlueDark,
    onSurface = OnSurfaceBlueDark,
    secondary = SecondaryBlueDark
)

@Composable
fun PBMPraktikumTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}