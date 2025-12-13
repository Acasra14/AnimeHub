// Archivo: com/example/animehub/ui/screens/AboutScreen.kt
package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.animehub.R
import com.example.animehub.ui.components.StandardTextComp // Reutilizamos el componente

// Esta función debe contener el cuerpo de la pantalla que diseñaste originalmente.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.nav_about)) })
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            StandardTextComp(
                text = stringResource(R.string.splash_subtitle),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(24.dp))

            FeatureCard(
                title = stringResource(R.string.contact_email),
                content = "animehub.support@example.com"
            )
            Spacer(modifier = Modifier.height(16.dp))
            FeatureCard(
                title = stringResource(R.string.api_documentation),
                content = "Utilizamos Jikan API para obtener la información más reciente de animes y mangas."
            )
        }
    }
}

// Componente auxiliar que se asume de tu código base
@Composable
fun FeatureCard(title: String, content: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            StandardTextComp(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            StandardTextComp(text = content, style = MaterialTheme.typography.bodyMedium)
        }
    }
}