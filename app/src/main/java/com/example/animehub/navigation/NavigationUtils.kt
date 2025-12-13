// Archivo: com/example/animehub/navigation/NavigationUtils.kt
package com.example.animehub.navigation

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

enum class NavigationType {
    BOTTOM_BAR, // Compact
    NAVIGATION_RAIL, // Medium
    PERMANENT_DRAWER // Expanded
}

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun getNavigationType(activity: ComponentActivity): NavigationType {
    val windowSizeClass = calculateWindowSizeClass(activity)
    return when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> NavigationType.BOTTOM_BAR
        WindowWidthSizeClass.Medium -> NavigationType.NAVIGATION_RAIL
        else -> NavigationType.PERMANENT_DRAWER // Expanded
    }
}