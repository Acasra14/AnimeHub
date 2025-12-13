package com.example.animehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.animehub.navigation.NavDestinations
import com.example.animehub.navigation.NavigationType
import com.example.animehub.navigation.PrimaryNavItems
import com.example.animehub.navigation.getNavigationType
import com.example.animehub.ui.components.BottomNavigationBarComp
import com.example.animehub.ui.components.NavigationRailComp
import com.example.animehub.ui.components.PermanentNavigationDrawerComp
import com.example.animehub.ui.screens.AboutScreen
import com.example.animehub.ui.screens.ElementDetailsScreen
import com.example.animehub.ui.screens.ElementListScreen
import com.example.animehub.ui.screens.FavDetailsScreen
import com.example.animehub.ui.screens.FavListScreen
import com.example.animehub.ui.screens.ProfileScreen
import com.example.animehub.ui.theme.AnimeHubTheme
import com.example.animehub.viewmodel.AnimeViewModel
import androidx.compose.runtime.toMutableStateList
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navType = getNavigationType(this)

            AnimeHubTheme {
                AnimeHubApp(navType)
            }
        }
    }
}
@Composable
fun AnimeHubApp(navType: NavigationType) {
    val navController = rememberNavController()
    val viewModel: AnimeViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val content: @Composable (innerPadding: PaddingValues) -> Unit = { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavDestinations.ELEMENT_LIST,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(NavDestinations.ELEMENT_LIST) {
                ElementListScreen(
                    elements = viewModel.animeList,
                    navController = navController,
                    isCompact = navType == NavigationType.BOTTOM_BAR,
                    onFavoriteToggle = viewModel::toggleFavorite
                )
            }
            composable(NavDestinations.FAV_LIST) {
                val favList = remember(viewModel.animeList) {
                    viewModel.animeList.filter { it.isFavorite }.toMutableStateList()
                }
                FavListScreen(
                    elements = favList,
                    navController = navController,
                    isCompact = navType == NavigationType.BOTTOM_BAR,
                    onRemoveFavorite = viewModel::removeFavorite
                )
            }
            composable(NavDestinations.PROFILE) {
                ProfileScreen()
            }
            composable(NavDestinations.ABOUT) {
                AboutScreen()
            }
            composable(NavDestinations.ELEMENT_DETAILS) { backStackEntry ->
                val name = backStackEntry.arguments?.getString(NavDestinations.ELEMENT_NAME_ARG) ?: "Error"
                ElementDetailsScreen(name = name, navController = navController)
            }
            composable(NavDestinations.FAV_DETAILS) { backStackEntry ->
                val name = backStackEntry.arguments?.getString(NavDestinations.ELEMENT_NAME_ARG) ?: "Error"
                FavDetailsScreen(name = name, navController = navController)
            }
        }
    }

    when (navType) {
        NavigationType.BOTTOM_BAR -> {
            Scaffold(
                bottomBar = {
                    if (currentRoute == NavDestinations.ELEMENT_LIST ||
                        currentRoute == NavDestinations.FAV_LIST ||
                        currentRoute == NavDestinations.PROFILE ||
                        currentRoute == NavDestinations.ABOUT)
                    {
                        BottomNavigationBarComp(navController, currentRoute, PrimaryNavItems)
                    }
                }
            ) { paddingValues ->
                content(paddingValues)
            }
        }
        NavigationType.NAVIGATION_RAIL -> {
            Row(Modifier.fillMaxSize()) {
                NavigationRailComp(navController, currentRoute, PrimaryNavItems)
                content(PaddingValues(horizontal = 8.dp))
            }
        }
        NavigationType.PERMANENT_DRAWER -> {
            PermanentNavigationDrawerComp(navController, currentRoute, PrimaryNavItems) {
                content(PaddingValues(0.dp))
            }
        }
    }
}