// Archivo: com/example/animehub/ui/screens/FavListScreen.kt (LIMPIO Y CORREGIDO)
package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animehub.R
import com.example.animehub.data.Datasource
import com.example.animehub.navigation.NavDestinations
import com.example.animehub.ui.components.FavCardCompact
import com.example.animehub.ui.components.FavCardMedExp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavListScreen(navController: NavController, isCompact: Boolean = true) {
    val allElements = remember { Datasource.getAnimeElements() }
    val favElements = allElements.filter { it.isFavorite }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.nav_favs)) }) }
    ) { paddingValues ->
        if (favElements.isEmpty()) {
            Text(stringResource(R.string.fav_list_empty), Modifier.padding(paddingValues).padding(16.dp))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                items(favElements, key = { it.name }) { element ->
                    val onDetailsClick = { navController.navigate(NavDestinations.createFavDetailsRoute(element.name)) }

                    if (isCompact) {
                        FavCardCompact(
                            element = element,
                            onRemoveClick = {  },
                            onDetailsClick = onDetailsClick
                        )
                    } else {
                        FavCardMedExp(
                            element = element,
                            onRemoveClick = { },
                            onDetailsClick = onDetailsClick
                        )
                    }
                }
            }
        }
    }
}