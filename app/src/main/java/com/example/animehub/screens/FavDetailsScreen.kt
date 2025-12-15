package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animehub.R
import com.example.animehub.ui.components.StandardTextComp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavDetailsScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.fav_details_title, "Anime Name")) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Comment")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
            StandardTextComp(text = "Detalles del Anime Favorito y Lista de Comentarios:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            StandardTextComp(text = "- Gran banda sonora.", modifier = Modifier.padding(start = 8.dp))
            StandardTextComp(text = "- Visto 3 veces.", modifier = Modifier.padding(start = 8.dp))
        }
    }
}