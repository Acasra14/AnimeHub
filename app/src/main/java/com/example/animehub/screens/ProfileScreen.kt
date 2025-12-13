// Archivo: com/example/animehub/ui/screens/ProfileScreen.kt
package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.animehub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    // Controlar el estado de login/logout (Requisito)
    var isLoggedIn by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.nav_profile)) }) }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (isLoggedIn) stringResource(R.string.profile_status_logged_in) else stringResource(R.string.profile_status_logged_out),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(24.dp))
            // El botón debe cambiar el texto al pulsarlo (Requisito)
            Button(onClick = {
                isLoggedIn = !isLoggedIn
            }) {
                Text(if (isLoggedIn) stringResource(R.string.logout_button) else stringResource(R.string.login_button))
            }
        }
    }
}