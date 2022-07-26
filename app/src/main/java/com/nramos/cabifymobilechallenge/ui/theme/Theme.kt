package com.nramos.cabifymobilechallenge.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = PrimaryPurple,
    primaryVariant = PrimaryPurple,
    secondary = SecondaryPurple,
    secondaryVariant = SecondaryPurple,
    background = BackgroundBlack,
    onBackground = BackgroundWhite,
    onSecondary = BackgroundWhite,
    onPrimary = BackgroundBlack
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = PrimaryPurple,
    primaryVariant = SecondaryPurple,
    secondary = SecondaryPurple,
    secondaryVariant = SecondaryPurple,
    background = BackgroundWhite,
    onBackground = BackgroundBlack,
    onSecondary = BackgroundWhite,
    onPrimary = BackgroundBlack

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CabifyChallengeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = !darkTheme
    )

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}