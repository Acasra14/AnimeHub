// Archivo: com/example/animehub/ui/screens/FavListScreen.kt
package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animehub.R
import com.example.animehub.model.AnimeElement
import com.example.animehub.navigation.NavDestinations
import com.example.animehub.ui.components.ConfirmDeleteDialog
import com.example.animehub.ui.components.FavElementCard
import com.example.animehub.ui.components.FavElementCardLand

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavListScreen(
    elements: List<AnimeElement>, // Lista filtrada de favoritos
    navController: NavController,
    isCompact: Boolean,
    onRemoveFavorite: (String) -> Unit, // Callback al ViewModel
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    var elementNameToRemove by remember { mutableStateOf<String?>(null) }

    // Diálogo de Borrado (Requisito)
    if (showDialog && elementNameToRemove != null) {
        ConfirmDeleteDialog(
            elementName = elementNameToRemove!!,
            onCancel = { showDialog = false; elementNameToRemove = null },
            onConfirm = {
                onRemoveFavorite(elementNameToRemove!!) // Llama a la función del ViewModel
                showDialog = false
                elementNameToRemove = null
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.nav_favs)) })
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (elements.isEmpty()) {
                Text(
                    text = stringResource(R.string.fav_list_empty),
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
                    items(elements, key = { it.name }) { element ->
                        val onRemoveClick = {
                            elementNameToRemove = element.name
                            showDialog = true // Muestra el diálogo antes de borrar
                        }
                        val onDetailsClick = {
                            navController.navigate(NavDestinations.createFavDetailsRoute(element.name))
                        }

                        if (isCompact) {
                            FavElementCard(
                                element = element,
                                onRemoveClick = onRemoveClick,
                                onDetailsClick = onDetailsClick
                            )
                        } else {
                            FavElementCardLand(
                                element = element,
                                onRemoveClick = onRemoveClick,
                                onDetailsClick = onDetailsClick
                            )
                        }
                    }
                }
            }
        }
    }
}