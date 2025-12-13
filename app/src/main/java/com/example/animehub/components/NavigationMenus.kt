package com.example.animehub.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animehub.navigation.NavItem
import com.example.animehub.navigation.NavDestinations
import com.example.animehub.R

@Composable
fun BottomNavigationBarComp(navController: NavController, currentRoute: String?, items: List<NavItem>) {
    NavigationBar {
        items.forEach { item ->
            val selected = currentRoute == item.route || currentRoute?.startsWith(item.route) == true
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = stringResource(item.labelResId)) },
                label = { Text(stringResource(item.labelResId)) },
                selected = selected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(NavDestinations.ELEMENT_LIST) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationRailComp(navController: NavController, currentRoute: String?, items: List<NavItem>) {
    NavigationRail(header = {
        Text("AnimeHub", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp))
    }) {
        items.forEach { item ->
            val selected = currentRoute == item.route || currentRoute?.startsWith(item.route) == true
            NavigationRailItem(
                icon = { Icon(item.icon, contentDescription = stringResource(item.labelResId)) },
                label = { Text(stringResource(item.labelResId)) },
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(NavDestinations.ELEMENT_LIST) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun PermanentNavigationDrawerComp(navController: NavController, currentRoute: String?, items: List<NavItem>, content: @Composable () -> Unit) {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(Modifier.fillMaxWidth(0.2f)) {
                // Título/Header del Drawer
                Text(
                    stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(24.dp)
                )

                items.forEach { item ->
                    val selected = currentRoute == item.route || currentRoute?.startsWith(item.route) == true
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(stringResource(item.labelResId)) },
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(NavDestinations.ELEMENT_LIST) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = content
    )
}