package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animehub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavDetailsScreen(name: String, navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.fav_details_title, name)) }) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Text(text = stringResource(R.string.user_comments_label), style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.comment_hint)) }
            )
            Button(onClick = { /* Guardar lógica */ }, modifier = Modifier.padding(top = 16.dp)) {
                Text(stringResource(R.string.save_comment))
            }
        }
    }
}