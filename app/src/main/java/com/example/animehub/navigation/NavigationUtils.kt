package com.example.animehub.navigation

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

enum class NavigationType {
    BOTTOM_BAR,
    NAVIGATION_RAIL,
    PERMANENT_DRAWER
}

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun getNavigationType(activity: ComponentActivity): NavigationType {
    val windowSizeClass = calculateWindowSizeClass(activity)
    return when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> NavigationType.BOTTOM_BAR
        WindowWidthSizeClass.Medium -> NavigationType.NAVIGATION_RAIL
        else -> NavigationType.PERMANENT_DRAWER
    }
}

object NavigationUtils {
    const val ELEMENT_LIST = "element_list"
    const val FAV_LIST = "fav_list"
    const val PROFILE = "profile"
    const val ABOUT = "about"
    const val ELEMENT_NAME_ARG = "name"
    const val ELEMENT_DETAILS = "element_details/{$ELEMENT_NAME_ARG}"
    const val FAV_DETAILS = "fav_details/{$ELEMENT_NAME_ARG}"

    fun createElementDetailsRoute(name: String) = "element_details/$name"
    fun createFavDetailsRoute(name: String) = "fav_details/$name"
}