// Archivo: com/example/animehub/ui/screens/ElementDetailsScreen.kt
package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElementDetailsScreen(name: String, navController: NavController, modifier: Modifier = Modifier) {
    val element = Datasource.getElementByName(name)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(element?.name ?: stringResource(R.string.details_title)) })
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            element?.let {
                // Imagen del elemento
                ImageComp(
                    drawable = Datasource.getDrawableIdByName(it.photo),
                    contentDesc = stringResource(R.string.element_image_desc, it.name),
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .padding(16.dp)
                        .widthIn(200.dp, 300.dp)
                        .fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(16.dp))
                StandardTextComp(text = it.name, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StandardTextComp(text = "Rank: ${it.rank}")
                    StandardTextComp(text = "Score: ${it.score}")
                }
                Spacer(modifier = Modifier.height(16.dp))

                StandardTextComp(
                    text = it.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            } ?: StandardTextComp(
                stringResource(R.string.element_not_found),
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(32.dp))
            StandardButtonComp(
                label = stringResource(R.string.back),
                onClick = { navController.navigateUp() }
            )
        }
    }
}