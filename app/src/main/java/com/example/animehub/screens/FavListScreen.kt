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
import com.example.animehub.model.AnimeElement
import com.example.animehub.navigation.NavDestinations
import com.example.animehub.ui.components.DeleteConfirmationDialog
import com.example.animehub.ui.components.FavCardCompact
import com.example.animehub.viewmodel.AnimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavListScreen(navController: NavController, isCompact: Boolean, viewModel: AnimeViewModel) {
    // 1. Recolectamos la lista de la base de datos (Formato AnimeEntity)
    val favEntities by viewModel.favoriteAnimes.collectAsState()

    // 2. SOLUCIÓN: Traducimos (mapeamos) la lista al formato de la interfaz (AnimeElement)
    val favElements = favEntities.map { entity ->
        AnimeElement(
            id = entity.id,
            name = entity.name,
            rank = entity.rank,
            score = entity.score,
            photo = entity.photoUrl,
            description = entity.description,
            isFavorite = true // Si está en la base de datos, obviamente es favorito
        )
    }

    var elementToDelete by remember { mutableStateOf<AnimeElement?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.nav_favs)) }) }
    ) { paddingValues ->
        if (favElements.isEmpty()) {
            Text(stringResource(R.string.fav_list_empty), Modifier.padding(paddingValues))
        } else {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                // Ahora iteramos sobre favElements (que ya son AnimeElement)
                items(favElements) { element ->
                    FavCardCompact(
                        element = element,
                        onDetailsClick = { navController.navigate(NavDestinations.createFavDetailsRoute(element.name)) },
                        onRemoveClick = { elementToDelete = element }
                    )
                }
            }
        }

        elementToDelete?.let { anime ->
            DeleteConfirmationDialog(
                onConfirm = {
                    viewModel.removeFavorite(anime.id)
                    elementToDelete = null
                },
                onDismiss = { elementToDelete = null }
            )
        }
    }
}