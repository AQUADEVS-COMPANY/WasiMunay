package com.aquadevs.wasimunay.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class ComposeTheme(
    val primary : Color = Color(0xFFD0BCFF),
    val secondary : Color = Color(0xFFCCC2DC),
    val background : Color = Color(0xFFFFFBFE),
    val onPrimary : Color = Color.White,
    val onSecondary : Color = Color.White,
    val surface : Color = Color(0xFFFFFBFE),
    val onBackground : Color = Color(0xFF1C1B1F),
    val onSurface : Color = Color(0xFF1C1B1F),
    val success : Color = Color(0xFF1C1B1F),
)

@Composable
fun validateTheme():ComposeTheme {
    return ComposeTheme(
        primary = if (isSystemInDarkTheme()) PrimaryDark else PrimaryLight,
        onPrimary = if (isSystemInDarkTheme()) OnPrimaryDark else OnPrimaryLight,
        onSecondary = if (isSystemInDarkTheme()) OnSecondaryDark else OnSecondaryLight,
        surface = if (isSystemInDarkTheme()) BackgroundDark else BackgroundLight,
        background = if (isSystemInDarkTheme()) BackgroundDark else BackgroundLight,
        onBackground = if (isSystemInDarkTheme()) OnBackgroundDark else OnBackgroundLight,
        onSurface = if (isSystemInDarkTheme()) OnSurfaceDark else OnSurfaceLight,
        success = if (isSystemInDarkTheme()) BackgroundSuccessDark else BackgroundSuccessLight,
    )
}