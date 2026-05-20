package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animehub.R
import com.example.animehub.model.AnimeElement
import com.example.animehub.navigation.NavDestinations
import com.example.animehub.ui.components.ElemCardCompact
import com.example.animehub.ui.components.ElemCardMedExp
import com.example.animehub.viewmodel.AnimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElemListScreen(navController: NavController, isCompact: Boolean, viewModel: AnimeViewModel) {

    // Recolectamos la lista en tiempo real (reacciona a la API y a Room automáticamente)
    val animeList by viewModel.animeList.collectAsState()

    // Aplicamos el filtro de búsqueda directamente sobre el estado reactivo
    val elements = if (viewModel.searchQuery.isEmpty()) {
        animeList
    } else {
        animeList.filter {
            it.name.contains(viewModel.searchQuery, ignoreCase = true) ||
                    it.description.contains(viewModel.searchQuery, ignoreCase = true)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = viewModel.searchQuery,
                        onValueChange = { viewModel.searchQuery = it },
                        label = { Text(stringResource(R.string.search_hint)) },
                        modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                        singleLine = true
                    )
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(elements) { element ->
                val onDetailsClick = { navController.navigate(NavDestinations.createElementDetailsRoute(element.name)) }

                if (isCompact) {
                    ElemCardCompact(
                        element = element,
                        onDetailsClick = onDetailsClick,
                        onFavClick = { viewModel.onFavoriteIconClicked(element) } // Guarda en BD o lanza Toast
                    )
                } else {
                    ElemCardMedExp(
                        element = element,
                        onDetailsClick = onDetailsClick,
                        onFavClick = { viewModel.onFavoriteIconClicked(element) } // Guarda en BD o lanza Toast
                    )
                }
            }
        }
    }
}

@Composable
fun ElemCardMedExp(element: AnimeElement, onDetailsClick: () -> Unit, onFavClick: () -> Unit) {
    TODO("Not yet implemented")
}