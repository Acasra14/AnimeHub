package com.example.animehub.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage // <-- LIBRERÍA COIL
import com.example.animehub.R
import com.example.animehub.ui.theme.CustomAction

@Composable
fun ImageComp(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    photoUrl: String, // <-- AHORA RECIBE UN STRING CON LA URL
    contentDesc: String = "",
    height: Int = 0,
    width: Int = 0
) {
    val contentDescription = if (contentDesc.isEmpty()) stringResource(id = R.string.default_content_descrip) else contentDesc

    // AsyncImage descarga y muestra la imagen de la API automáticamente
    AsyncImage(
        model = photoUrl,
        contentDescription = contentDescription,
        modifier = modifier.height(height.dp).width(width.dp),
        contentScale = contentScale
    )
}

@Composable
fun StandardButtonComp(label: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Button(
        modifier = modifier.padding(8.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = CustomAction, contentColor = MaterialTheme.colorScheme.onPrimary)
    ) { Text(text = label) }
}

@Composable
fun StandardTextComp(text: String, modifier: Modifier = Modifier, style: TextStyle = MaterialTheme.typography.bodyMedium) {
    Text(modifier = modifier, text = text, style = style)
}