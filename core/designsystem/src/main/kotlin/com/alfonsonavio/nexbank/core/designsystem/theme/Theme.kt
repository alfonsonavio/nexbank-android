package com.alfonsonavio.nexbank.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * NexBank theme with the brand colour schemes
 *
 * Dynamic colour (Android 12+) is intentionally not supported: a banking app keeps its brand identity regardless of the user's wallpaper.
 */
@Composable
fun NexBankTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = NexBankTypography,
        content = content,
    )
}
