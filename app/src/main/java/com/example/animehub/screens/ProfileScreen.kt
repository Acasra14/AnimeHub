package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.animehub.R
import com.example.animehub.data.ThemePreference
import com.example.animehub.ui.components.StandardButtonComp
import com.example.animehub.viewmodel.AnimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: AnimeViewModel) {
    // Leemos los datos en tiempo real desde el ViewModel (DataStore)
    val currentUsername by viewModel.username.collectAsState()
    val currentTheme by viewModel.themePreference.collectAsState()

    // Variable temporal para lo que el usuario está escribiendo en el campo de texto
    var tempName by remember { mutableStateOf(currentUsername) }

    // Si el nombre cambia en la memoria, actualizamos el campo de texto
    LaunchedEffect(currentUsername) {
        tempName = currentUsername
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Ajustes de Usuario", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        // Campo de Texto para el Nombre
        OutlinedTextField(
            value = tempName,
            onValueChange = { tempName = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        StandardButtonComp(
            label = "Guardar Nombre",
            onClick = { viewModel.saveUsername(tempName) }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Selección de Tema
        Text(text = "Apariencia", style = MaterialTheme.typography.titleMedium)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ThemeRadioButton(
                label = "Claro",
                selected = currentTheme == ThemePreference.LIGHT,
                onClick = { viewModel.saveTheme(ThemePreference.LIGHT) }
            )
            ThemeRadioButton(
                label = "Oscuro",
                selected = currentTheme == ThemePreference.DARK,
                onClick = { viewModel.saveTheme(ThemePreference.DARK) }
            )
            ThemeRadioButton(
                label = "Sistema",
                selected = currentTheme == ThemePreference.SYSTEM,
                onClick = { viewModel.saveTheme(ThemePreference.SYSTEM) }
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Botón de Login Antiguo (Lo mantenemos por si acaso)
        Text(
            text = if (viewModel.isLoggedIn) stringResource(R.string.profile_status_logged_in)
            else stringResource(R.string.profile_status_logged_out)
        )
        StandardButtonComp(
            label = if (viewModel.isLoggedIn) stringResource(R.string.logout_button)
            else stringResource(R.string.login_button),
            onClick = { viewModel.isLoggedIn = !viewModel.isLoggedIn }
        )
    }
}

// Componente de ayuda para dibujar los botones de radio más fácilmente
@Composable
fun ThemeRadioButton(label: String, selected: Boolean, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected, onClick = onClick)
        Text(text = label)
    }
}