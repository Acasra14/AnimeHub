package com.example.animehub.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.animehub.R
import com.example.animehub.model.AnimeElement
import com.example.animehub.ui.theme.CustomAction

@Composable
fun ElemCardCompact(element: AnimeElement, onDetailsClick: () -> Unit, onFavClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 8.dp).clickable { onDetailsClick() },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            ImageComp(photoUrl = element.photo, height = 80, width = 80)
            Column(modifier = Modifier.weight(1f).padding(horizontal = 16.dp)) {
                StandardTextComp(text = element.name, style = MaterialTheme.typography.titleMedium)
                StandardTextComp(text = stringResource(R.string.element_rank, element.rank))
            }
            IconButton(onClick = onFavClick) {
                Icon(
                    imageVector = if (element.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorito",
                    tint = if (element.isFavorite) CustomAction else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun ElemCardMedExp(element: AnimeElement, onDetailsClick: () -> Unit, onFavClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp).clickable { onDetailsClick() },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            ImageComp(photoUrl = element.photo, height = 120, width = 120)
            Column(modifier = Modifier.weight(1f).padding(horizontal = 24.dp)) {
                StandardTextComp(text = element.name, style = MaterialTheme.typography.headlineSmall)
                StandardTextComp(text = stringResource(R.string.element_rank, element.rank))
                StandardTextComp(text = element.description, style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = onFavClick) {
                Icon(
                    imageVector = if (element.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorito",
                    tint = if (element.isFavorite) CustomAction else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun FavCardCompact(element: AnimeElement, onRemoveClick: () -> Unit, onDetailsClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 8.dp).clickable { onDetailsClick() },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            ImageComp(photoUrl = element.photo, height = 80, width = 80)
            Column(modifier = Modifier.weight(1f).padding(horizontal = 16.dp)) {
                StandardTextComp(text = element.name, style = MaterialTheme.typography.titleMedium)
            }
            IconButton(onClick = onRemoveClick) {
                Icon(Icons.Filled.Clear, contentDescription = "Borrar", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun FavCardMedExp(element: AnimeElement, onRemoveClick: () -> Unit, onDetailsClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp).clickable { onDetailsClick() },
        shape = MaterialTheme.shapes.large
    ) {
        Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
            ImageComp(photoUrl = element.photo, height = 120, width = 120)
            Column(modifier = Modifier.weight(1f).padding(horizontal = 24.dp)) {
                StandardTextComp(text = element.name, style = MaterialTheme.typography.headlineSmall)
            }
            IconButton(onClick = onRemoveClick) {
                Icon(Icons.Filled.Clear, contentDescription = "Borrar", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}