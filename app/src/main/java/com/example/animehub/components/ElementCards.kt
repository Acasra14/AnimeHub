package com.example.animehub.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.animehub.R
import com.example.animehub.data.Datasource
import com.example.animehub.model.AnimeElement
import com.example.animehub.ui.theme.CustomAction

@Composable
fun ElemCardCompact(
    element: AnimeElement,
    onDetailsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isFavorite by remember { mutableStateOf(element.isFavorite) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onDetailsClick() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ImageComp(
                drawable = Datasource.getDrawableIdByName(element.photo),
                height = 80, width = 80
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                StandardTextComp(text = element.name, style = MaterialTheme.typography.titleMedium)
                StandardTextComp(text = stringResource(R.string.element_rank, element.rank))
            }

            IconButton(onClick = { isFavorite = !isFavorite }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = stringResource(R.string.favorite_toggle_desc),
                    tint = if (isFavorite) CustomAction else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun ElemCardMedExp(
    element: AnimeElement,
    onDetailsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isFavorite by remember { mutableStateOf(element.isFavorite) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onDetailsClick() },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            ImageComp(
                drawable = Datasource.getDrawableIdByName(element.photo),
                height = 120, width = 120
            )

            Column(modifier = Modifier.weight(1f)) {
                StandardTextComp(text = element.name, style = MaterialTheme.typography.headlineSmall)
                StandardTextComp(text = stringResource(R.string.element_rank, element.rank))
                StandardTextComp(text = element.description, style = MaterialTheme.typography.bodyMedium)
            }

            IconButton(onClick = { isFavorite = !isFavorite }, modifier = Modifier.size(56.dp)) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = stringResource(R.string.favorite_toggle_desc),
                    tint = if (isFavorite) CustomAction else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun FavCardCompact(element: AnimeElement, onRemoveClick: () -> Unit, onDetailsClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onDetailsClick() },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ImageComp(
                drawable = Datasource.getDrawableIdByName(element.photo),
                height = 80, width = 80
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                StandardTextComp(text = element.name, style = MaterialTheme.typography.titleMedium)
                StandardTextComp(text = stringResource(R.string.element_rank, element.rank))
            }

            IconButton(onClick = onRemoveClick) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = stringResource(R.string.delete_desc),
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun FavCardMedExp(element: AnimeElement, onRemoveClick: () -> Unit, onDetailsClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onDetailsClick() },
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            ImageComp(
                drawable = Datasource.getDrawableIdByName(element.photo),
                height = 120, width = 120
            )

            Column(modifier = Modifier.weight(1f)) {
                StandardTextComp(text = element.name, style = MaterialTheme.typography.headlineSmall)
                StandardTextComp(text = element.description, style = MaterialTheme.typography.bodyMedium)
            }

            IconButton(onClick = onRemoveClick, modifier = Modifier.size(56.dp)) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = stringResource(R.string.delete_desc),
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}