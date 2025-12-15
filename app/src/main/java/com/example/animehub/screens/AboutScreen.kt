package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.animehub.R
import com.example.animehub.ui.components.StandardTextComp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.nav_about)) }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
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