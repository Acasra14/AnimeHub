package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.animehub.R
import com.example.animehub.navigation.NavDestinations
import com.example.animehub.ui.components.DeleteConfirmationDialog
import com.example.animehub.ui.components.FavCardCompact
import com.example.animehub.viewmodel.AnimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavListScreen(navController: NavController, isCompact: Boolean, viewModel: AnimeViewModel) {
    val favElements = viewModel.animeList.filter { it.isFavorite }
    var elementToDelete by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.nav_favs)) }) }
    ) { paddingValues ->
        if (favElements.isEmpty()) {
            Text(stringResource(R.string.fav_list_empty), Modifier.padding(paddingValues))
        } else {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(favElements) { element ->
                    FavCardCompact(
                        element = element,
                        onDetailsClick = { navController.navigate(NavDestinations.createFavDetailsRoute(element.name)) },
                        onRemoveClick = { elementToDelete = element.name }
                    )
                }
            }
        }

        elementToDelete?.let { name ->
            DeleteConfirmationDialog(
                onConfirm = {
                    viewModel.toggleFavorite(name)
                    elementToDelete = null
                },
                onDismiss = { elementToDelete = null }
            )
        }
    }
}