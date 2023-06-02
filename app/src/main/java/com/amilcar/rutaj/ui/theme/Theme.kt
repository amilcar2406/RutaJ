package com.amilcar.rutaj.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary =   Cyan900,
    primaryVariant = Gray700,
    secondary = Gray400,
    secondaryVariant = Cyan600,
    background = Gray800,
    surface = Gray700,
    error = Red500,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Gray200,
    onSurface = Color.White,
    onError = Color.White
)

private val LightColorPalette = lightColors(
    //primary =   LightBlue900,
    primary = Color(0xFF043369),
    primaryVariant = Cyan700,
    secondary = Cyan500,
    secondaryVariant = Cyan700,
    background = Blue50,
    surface = White,
    error = Red600,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.Black
)


@Composable
fun RutaJTheme(darkTheme : Boolean = isSystemInDarkTheme(), content : @Composable() () -> Unit) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Black)


    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
     //   DarkColorPalette
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}