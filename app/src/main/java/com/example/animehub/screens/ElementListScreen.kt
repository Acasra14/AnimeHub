package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animehub.R
import com.example.animehub.data.Datasource
import com.example.animehub.navigation.NavDestinations
import com.example.animehub.ui.components.ElemCardCompact
import com.example.animehub.ui.components.ElemCardMedExp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElemListScreen(
    navController: NavController,
    isCompact: Boolean,
    modifier: Modifier = Modifier
) {
    val elements = remember { Datasource.getAnimeElements() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.nav_list)) },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(NavDestinations.FAV_LIST) },
                        content = {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = stringResource(R.string.nav_favs)
                            )
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 4.dp)
        ) {
            items(elements, key = { it.name }) { element ->
                val onDetailsClick = {
                    navController.navigate(NavDestinations.createElementDetailsRoute(element.name))
                }

                if (isCompact) {
                    ElemCardCompact(
                        element = element,
                        onDetailsClick = onDetailsClick
                    )
                } else {
                    ElemCardMedExp(
                        element = element,
                        onDetailsClick = onDetailsClick
                    )
                }
            }
        }
    }
}