package app.waste2wealth.com.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.material.lightColors


val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val backGround = Color(0xFF21c998)
val CardBackground = Color(0xFF122754)
val lightText = Color(0xFF3f68a8)


@SuppressLint("ConflictingOnColor")
val LightColors = lightColors(
    primary = Palette.md_theme_light_primary,
    onPrimary = Palette.md_theme_light_onPrimary,
    secondary = Palette.md_theme_light_secondary,
    onSecondary = Palette.md_theme_light_onSecondary,
    error = Palette.md_theme_light_error,
    onError = Palette.md_theme_light_onError,
    background = Palette.md_theme_light_background,
    onBackground = Palette.md_theme_light_onBackground,
    surface = Palette.md_theme_light_surface,
    onSurface = Palette.md_theme_light_onSurface,
)


val DarkColors = darkColors(
    primary = Palette.md_theme_dark_primary,
    onPrimary = Palette.md_theme_dark_onPrimary,
    secondary = Palette.md_theme_dark_secondary,
    onSecondary = Palette.md_theme_dark_onSecondary,
    error = Palette.md_theme_dark_error,
    onError = Palette.md_theme_dark_onError,
    background = Palette.md_theme_dark_background,
    onBackground = Palette.md_theme_dark_onBackground,
    surface = Palette.md_theme_dark_surface,
    onSurface = Palette.md_theme_dark_onSurface,
)


val isDarkThemeEnabled: Boolean
    @Composable
    get() = isSystemInDarkTheme()

val appBackground: Color
    @Composable
    get() = MaterialTheme.colors.onPrimary

val CardColor: Color
    @Composable
    get() = MaterialTheme.colors.secondary

val CardTextColor: Color
    @Composable
    get() = MaterialTheme.colors.surface

val textColor: Color
    @Composable
    get() = MaterialTheme.colors.onSurface
