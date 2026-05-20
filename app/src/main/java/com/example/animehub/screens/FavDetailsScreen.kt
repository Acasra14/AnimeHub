package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animehub.R
import com.example.animehub.ui.components.ImageComp
import com.example.animehub.ui.components.StandardTextComp
import com.example.animehub.viewmodel.AnimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavDetailsScreen(name: String, navController: NavController, viewModel: AnimeViewModel) {

    val animes by viewModel.favoriteAnimes.collectAsState()
    val anime = animes.find { it.name == name }

    if (anime == null) {
        Text("Cargando o no encontrado...")
        return
    }

    val comments by viewModel.getCommentsForAnime(anime.id).collectAsState(initial = emptyList())

    var showDialog by remember { mutableStateOf(false) }
    var newCommentText by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.fav_details_title, anime.name)) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Comment")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
            StandardTextComp(text = "Comentarios de los usuarios:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))

            if (comments.isEmpty()) {
                Text("Aún no hay comentarios. ¡Sé el primero!")
            } else {
                LazyColumn {
                    items(comments) { comment ->
                        Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                            // MODIFICADO: Row para poner el texto a la izquierda y el botón de borrar a la derecha
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(text = comment.authorName, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = comment.text, style = MaterialTheme.typography.bodyMedium)
                                }

                                // AÑADIDO: Botón de borrado de comentario
                                IconButton(onClick = { viewModel.removeComment(comment.commentId) }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Borrar comentario",
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Nuevo Comentario") },
                text = {
                    OutlinedTextField(
                        value = newCommentText,
                        onValueChange = { newCommentText = it },
                        label = { Text("Escribe aquí...") }
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        if (newCommentText.isNotBlank()) {
                            viewModel.addComment(anime.id, newCommentText)
                            newCommentText = ""
                            showDialog = false
                        }
                    }) { Text("Guardar") }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) { Text("Cancelar") }
                }
            )
        }
    }
}