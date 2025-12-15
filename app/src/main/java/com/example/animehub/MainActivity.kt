package com.example.animehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animehub.navigation.NavDestinations
import com.example.animehub.navigation.NavigationType
import com.example.animehub.navigation.getNavigationType
import com.example.animehub.ui.screens.*
import com.example.animehub.ui.theme.AnimeHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeHubTheme {
                val navType = getNavigationType(this)
                AnimeHubApp(navType)
            }
        }
    }
}

@Composable
fun AnimeHubApp(navType: NavigationType) {
    val navController = rememberNavController()
    val isCompact = navType == NavigationType.BOTTOM_BAR

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavDestinations.ELEMENT_LIST,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavDestinations.ELEMENT_LIST) {
                ElemListScreen(navController = navController, isCompact = isCompact)
            }

            composable(NavDestinations.ELEMENT_DETAILS) { backStackEntry ->
                val name = backStackEntry.arguments?.getString(NavDestinations.ELEMENT_NAME_ARG) ?: "Error"
                ElementDetailsScreen(name = name, navController = navController, isCompact = isCompact)
            }

            // FavListScreen
            composable(NavDestinations.FAV_LIST) {
                FavListScreen(navController = navController)
            }

            composable(NavDestinations.FAV_DETAILS) {
                FavDetailsScreen(navController = navController)
            }

            composable(NavDestinations.PROFILE) {
                ProfileScreen()
            }

            composable(NavDestinations.ABOUT) {
                AboutScreen()
            }
        }
    }
}