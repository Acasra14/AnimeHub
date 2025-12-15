package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animehub.R
import com.example.animehub.data.Datasource
import com.example.animehub.ui.components.ImageComp
import com.example.animehub.ui.components.StandardButtonComp
import com.example.animehub.ui.components.StandardTextComp
import com.example.animehub.ui.theme.CustomAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElementDetailsScreen(name: String, navController: NavController, isCompact: Boolean, modifier: Modifier = Modifier) {
    val element = Datasource.getElementByName(name)
    var isFavorite by remember { mutableStateOf(element?.isFavorite ?: false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(element?.name ?: stringResource(R.string.details_title)) }) }
    ) { paddingValues ->
        val contentModifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)

        element?.let {
            if (isCompact) {
                Column(modifier = contentModifier, horizontalAlignment = Alignment.CenterHorizontally) {
                    ImageComp(drawable = Datasource.getDrawableIdByName(it.photo), height = 200, width = 200)
                    Spacer(modifier = Modifier.height(16.dp))
                    StandardTextComp(text = it.name, style = MaterialTheme.typography.headlineMedium)
                    StandardTextComp(text = stringResource(R.string.element_rank, it.rank), style = MaterialTheme.typography.bodyMedium)
                    StandardTextComp(text = it.description, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(24.dp))
                    FavoriteButton(isFavorite) { isFavorite = !isFavorite }
                }
            } else {
                // Layout Medio/Expandido (Horizontal)
                Row(modifier = contentModifier, horizontalArrangement = Arrangement.SpaceBetween) {
                    ImageComp(drawable = Datasource.getDrawableIdByName(it.photo), height = 300, width = 300, contentScale = ContentScale.Crop)
                    Column(modifier = Modifier.weight(1f).padding(start = 24.dp)) {
                        StandardTextComp(text = it.name, style = MaterialTheme.typography.headlineLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        StandardTextComp(text = stringResource(R.string.element_rank, it.rank), style = MaterialTheme.typography.bodyMedium)
                        StandardTextComp(text = it.description, style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(24.dp))
                        FavoriteButton(isFavorite) { isFavorite = !isFavorite }
                        StandardButtonComp(label = stringResource(R.string.back), onClick = { navController.navigateUp() })
                    }
                }
            }
        } ?: StandardTextComp(stringResource(R.string.element_not_found))

        if (isCompact) {
        } else {
        }
    }
}

@Composable
private fun FavoriteButton(isFavorite: Boolean, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = stringResource(R.string.favorite_toggle_desc),
            tint = if (isFavorite) CustomAction else MaterialTheme.colorScheme.onPrimary
        )
        Spacer(Modifier.width(8.dp))
        Text(if (isFavorite) stringResource(R.string.remove_from_favs) else stringResource(R.string.add_to_favs))
    }
}