package com.example.animehub.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.animehub.R

object NavDestinations {
    const val ELEMENT_LIST = "element_list"
    const val FAV_LIST = "fav_list"
    const val PROFILE = "profile"
    const val ABOUT = "about"

    // Argumentos de ruta
    const val ELEMENT_NAME_ARG = "name"
    const val ELEMENT_DETAILS = "element_details/{$ELEMENT_NAME_ARG}"
    const val FAV_DETAILS = "fav_details/{$ELEMENT_NAME_ARG}"

    fun createElementDetailsRoute(name: String) = "element_details/$name"
    fun createFavDetailsRoute(name: String) = "fav_details/$name"
}

data class NavItem(
    val route: String,
    val icon: ImageVector,
    val labelResId: Int
)

val PrimaryNavItems = listOf(
    NavItem(NavDestinations.ELEMENT_LIST, Icons.AutoMirrored.Filled.List, R.string.nav_list),
    NavItem(NavDestinations.FAV_LIST, Icons.Default.Favorite, R.string.nav_favs),
    NavItem(NavDestinations.PROFILE, Icons.Default.Person, R.string.nav_profile),
    NavItem(NavDestinations.ABOUT, Icons.Default.Info, R.string.nav_about)
)