package com.example.animehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.animehub.data.ThemePreference
import com.example.animehub.navigation.*
import com.example.animehub.ui.screens.*
import com.example.animehub.ui.theme.AnimeHubTheme
import com.example.animehub.ui.components.*
import com.example.animehub.viewmodel.AnimeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 1. Instanciamos el ViewModel aquí arriba para poder leer el tema
            val viewModel: AnimeViewModel = viewModel()

            // 2. Leemos la preferencia guardada en DataStore
            val themePref by viewModel.themePreference.collectAsState()

            // 3. Calculamos si el tema debe ser oscuro en base a la configuración
            val isDarkTheme = when (themePref) {
                ThemePreference.LIGHT -> false
                ThemePreference.DARK -> true
                ThemePreference.SYSTEM -> isSystemInDarkTheme()
            }

            // 4. Aplicamos el color correspondiente al tema de Jetpack Compose
            AnimeHubTheme(darkTheme = isDarkTheme) {
                val navType = getNavigationType(this)
                AnimeHubApp(navType, viewModel)
            }
        }
    }
}

@Composable
fun AnimeHubApp(navType: NavigationType, viewModel: AnimeViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isCompact = navType == NavigationType.BOTTOM_BAR

    val appContent: @Composable (PaddingValues) -> Unit = { padding ->
        NavHost(
            navController = navController,
            startDestination = NavDestinations.ELEMENT_LIST,
            modifier = Modifier.padding(padding)
        ) {
            composable(NavDestinations.ELEMENT_LIST) {
                ElemListScreen(navController, isCompact, viewModel)
            }

            composable(NavDestinations.ELEMENT_DETAILS) { backStackEntry ->
                val name = backStackEntry.arguments?.getString(NavDestinations.ELEMENT_NAME_ARG) ?: ""
                ElementDetailsScreen(
                    name = name,
                    navController = navController,
                    isCompact = isCompact,
                    viewModel = viewModel
                )
            }

            composable(NavDestinations.FAV_LIST) {
                FavListScreen(navController, isCompact, viewModel)
            }

            composable(NavDestinations.FAV_DETAILS) { backStackEntry ->
                val name = backStackEntry.arguments?.getString(NavDestinations.ELEMENT_NAME_ARG) ?: ""
                // AÑADIDO: Ahora se le pasa el viewModel para cargar los comentarios de Room
                FavDetailsScreen(
                    name = name,
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable(NavDestinations.PROFILE) {
                ProfileScreen(viewModel = viewModel)
            }

            composable(NavDestinations.ABOUT) {
                AboutScreen()
            }
        }
    }

    if (navType == NavigationType.PERMANENT_DRAWER) {
        PermanentNavigationDrawerComp(navController, currentRoute, PrimaryNavItems) {
            Scaffold { padding -> appContent(padding) }
        }
    } else {
        Scaffold(
            bottomBar = {
                if (isCompact) {
                    BottomNavigationBarComp(navController, currentRoute, PrimaryNavItems)
                }
            }
        ) { padding ->
            Row(modifier = Modifier.fillMaxSize()) {
                if (navType == NavigationType.NAVIGATION_RAIL) {
                    NavigationRailComp(navController, currentRoute, PrimaryNavItems)
                }
                Box(modifier = Modifier.weight(1f)) {
                    appContent(padding)
                }
            }
        }
    }
}