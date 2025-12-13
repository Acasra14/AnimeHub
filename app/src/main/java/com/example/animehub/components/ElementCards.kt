package com.example.animehub.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.animehub.R
import com.example.animehub.data.Datasource
import com.example.animehub.model.AnimeElement

@Composable
fun ElementCard(
    element: AnimeElement,
    onDetailsClick: () -> Unit,
    onFavoriteToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onDetailsClick() },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ImageComp(
                drawable = Datasource.getDrawableIdByName(element.photo),
                height = 80, width = 80
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                StandardTextComp(text = element.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                StandardTextComp(text = stringResource(R.string.element_rank, element.rank))
                StandardTextComp(text = stringResource(R.string.element_score, element.score))
            }

            Column(verticalArrangement = Arrangement.SpaceAround) {
                IconButton(onClick = onFavoriteToggle, modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = if (element.isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(R.string.favorite_toggle_desc),
                        tint = if (element.isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                IconButton(onClick = onDetailsClick, modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = stringResource(R.string.more_content_desc),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun ElementCardLand(
    element: AnimeElement,
    onDetailsClick: () -> Unit,
    onFavoriteToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clickable { onDetailsClick() },
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ImageComp(
                drawable = Datasource.getDrawableIdByName(element.photo),
                height = 100, width = 100
            )

            Column(modifier = Modifier.weight(1f)) {
                StandardTextComp(text = element.name, style = MaterialTheme.typography.headlineSmall)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    StandardTextComp(text = stringResource(R.string.element_rank, element.rank))
                    StandardTextComp(text = stringResource(R.string.element_score, element.score))

                    IconButton(onClick = onFavoriteToggle, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = if (element.isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(R.string.favorite_toggle_desc),
                            tint = if (element.isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                StandardTextComp(text = element.description, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun FavElementCard(
    element: AnimeElement,
    onRemoveClick: () -> Unit,
    onDetailsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onDetailsClick() },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ImageComp(
                drawable = Datasource.getDrawableIdByName(element.photo),
                height = 80, width = 80
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                StandardTextComp(text = element.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                StandardTextComp(text = stringResource(R.string.element_rank, element.rank))
                StandardTextComp(text = stringResource(R.string.element_score, element.score))
            }

            Column(verticalArrangement = Arrangement.SpaceAround) {
                IconButton(onClick = onRemoveClick, modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(R.string.delete_desc),
                        tint = MaterialTheme.colorScheme.error
                    )
                }

                IconButton(onClick = onDetailsClick, modifier = Modifier.size(48.dp)) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = stringResource(R.string.more_content_desc),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun FavElementCardLand(
    element: AnimeElement,
    onRemoveClick: () -> Unit,
    onDetailsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clickable { onDetailsClick() },
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ImageComp(
                drawable = Datasource.getDrawableIdByName(element.photo),
                height = 100, width = 100
            )

            Column(modifier = Modifier.weight(1f)) {
                StandardTextComp(text = element.name, style = MaterialTheme.typography.headlineSmall)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    StandardTextComp(text = stringResource(R.string.element_rank, element.rank))
                    StandardTextComp(text = stringResource(R.string.element_score, element.score))

                    IconButton(onClick = onRemoveClick, modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(R.string.delete_desc),
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                StandardTextComp(text = element.description, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}