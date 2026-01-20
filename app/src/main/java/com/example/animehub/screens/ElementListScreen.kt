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
import com.example.animehub.navigation.NavDestinations
import com.example.animehub.ui.components.ElemCardCompact
import com.example.animehub.ui.components.ElemCardMedExp
import com.example.animehub.viewmodel.AnimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElemListScreen(navController: NavController, isCompact: Boolean, viewModel: AnimeViewModel) {
    val elements = viewModel.getFilteredList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = viewModel.searchQuery,
                        onValueChange = { viewModel.searchQuery = it },
                        label = { Text(stringResource(R.string.search_hint)) }, // Añade esta clave a strings.xml
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
                    ElemCardCompact(element, onDetailsClick, onFavClick = { viewModel.toggleFavorite(element.name) })
                } else {
                    ElemCardMedExp(element, onDetailsClick, onFavClick = { viewModel.toggleFavorite(element.name) })
                }
            }
        }
    }
}