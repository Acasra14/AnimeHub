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
import com.example.animehub.ui.components.ElementCard
import com.example.animehub.ui.components.ElementCardLand
import com.example.animehub.ui.components.StandardInputTextComp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElementListScreen(
    elements: List<AnimeElement>,
    navController: NavController,
    isCompact: Boolean,
    onFavoriteToggle: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }

    val filteredElements = remember(elements, searchText) {
        elements.filter { element ->
            element.name.contains(searchText, ignoreCase = true) ||
                    element.description.contains(searchText, ignoreCase = true)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.nav_list)) })
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            StandardInputTextComp(
                label = stringResource(R.string.search_hint),
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyColumn(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
                items(filteredElements, key = { it.name }) { element ->
                    val onDetailsClick = {
                        navController.navigate(NavDestinations.createElementDetailsRoute(element.name))
                    }

                    if (isCompact) {
                        ElementCard(
                            element = element,
                            onDetailsClick = onDetailsClick,
                            onFavoriteToggle = { onFavoriteToggle(element.name) }
                        )
                    } else {
                        ElementCardLand(
                            element = element,
                            onDetailsClick = onDetailsClick,
                            onFavoriteToggle = { onFavoriteToggle(element.name) }
                        )
                    }
                }
            }
        }
    }
}