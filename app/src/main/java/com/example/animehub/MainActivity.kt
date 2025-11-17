package com.example.animehub

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animehub.ui.theme.AnimeHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeHubTheme {
                AboutAnimeAppScreen()
            }
        }
    }
}

@Composable
fun AboutAnimeAppScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo de la aplicación
        Image(
            painter = painterResource(id = R.drawable.animehub),
            contentDescription = "Logo AnimeHub",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nombre de la aplicación
        Text(
            text = "AnimeHub",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Temática
        FeatureCard(
            title = "Temática",
            content = "Red Social de Anime y Manga"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Descripción
        FeatureCard(
            title = "Descripción",
            content = "AnimeHub es la comunidad definitiva para los fans del anime. " +
                    "Descubre nuevas series, guarda tus favoritas, comparte reviews " +
                    "y conecta con otros otakus. ¡Tu experiencia anime en una sola app!"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Características principales
        FeatureCard(
            title = "Características",
            content = "• Catálogo con miles de animes\n" +
                    "• Sistema de favoritos y listas\n" +
                    "• Comunidad y reviews\n" +
                    "• Noticias del mundo anime\n" +
                    "• Recomendaciones personalizadas"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // API utilizada
        FeatureCard(
            title = "API Utilizada",
            content = "Jikan API - Base de datos de MyAnimeList\n" +
                    "• Más de 15,000 animes\n" +
                    "• Información detallada de series\n" +
                    "• Imágenes y trailers\n" +
                    "• Calificaciones y reviews"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Versión
        FeatureCard(
            title = "Versión",
            content = "AnimeHub v1.0.0"
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botones de acción
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Botón de contacto
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:soporte@animehub.com")
                        putExtra(Intent.EXTRA_SUBJECT, "Consulta sobre AnimeHub")
                        putExtra(Intent.EXTRA_TEXT,
                            "Hola equipo de AnimeHub,\n\n" +
                                    "Me encanta la aplicación y quería contactar porque...\n\n" +
                                    "¡Arigatou!"
                        )
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Contactar",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Contactar al Equipo")
            }

            // Botón para ver documentación API
            OutlinedButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://jikan.moe/")
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver Documentación de Jikan API")
            }
        }
    }
}

@Composable
fun FeatureCard(title: String, content: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = content,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutAnimeAppScreenPreview() {
    AnimeHubTheme {
        AboutAnimeAppScreen()
    }
}