package com.example.animehub.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Creamos la instancia de DataStore (memoria física del dispositivo)
val Context.dataStore by preferencesDataStore(name = "user_prefs")

// Enum para saber qué tema ha elegido el usuario
enum class ThemePreference {
    LIGHT, DARK, SYSTEM
}

class UserPreferencesRepository(private val context: Context) {

    // Definimos las "claves" (nombres de las variables) para guardar los datos
    private val USERNAME_KEY = stringPreferencesKey("username")
    private val THEME_KEY = stringPreferencesKey("theme_pref")

    // Flujo que lee el nombre en tiempo real. Si no hay nada, devuelve "Otaku Anónimo"
    val usernameFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[USERNAME_KEY] ?: "Otaku Anónimo"
    }

    // Flujo que lee el tema. Por defecto devuelve SYSTEM (Según el sistema)
    val themeFlow: Flow<ThemePreference> = context.dataStore.data.map { prefs ->
        val themeString = prefs[THEME_KEY] ?: ThemePreference.SYSTEM.name
        ThemePreference.valueOf(themeString)
    }

    // Funciones Suspendidas (Corrutinas) para escribir en la memoria
    suspend fun saveUsername(name: String) {
        context.dataStore.edit { prefs ->
            prefs[USERNAME_KEY] = name
        }
    }

    suspend fun saveTheme(theme: ThemePreference) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = theme.name
        }
    }
}