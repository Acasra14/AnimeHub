package com.example.animehub.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.animehub.data.AnimeRepository
import com.example.animehub.data.ThemePreference
import com.example.animehub.data.UserPreferencesRepository
import com.example.animehub.data.local.AppDatabase
import com.example.animehub.model.AnimeElement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AnimeViewModel(application: Application) : AndroidViewModel(application) {

    // --- PREFERENCIAS DE USUARIO (DataStore) ---
    private val userPrefs = UserPreferencesRepository(application)
    val username: StateFlow<String> = userPrefs.usernameFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "Otaku Anónimo")
    val themePreference: StateFlow<ThemePreference> = userPrefs.themeFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ThemePreference.SYSTEM)

    fun saveUsername(name: String) = viewModelScope.launch { userPrefs.saveUsername(name) }
    fun saveTheme(theme: ThemePreference) = viewModelScope.launch { userPrefs.saveTheme(theme) }

    var isLoggedIn by mutableStateOf(false)
    var searchQuery by mutableStateOf("")

    // --- REPOSITORIO Y BASE DE DATOS ---
    private val db = AppDatabase.getDatabase(application)
    private val repository = AnimeRepository(db.animeDao())

    // --- ESTADOS DE LA UI ---
    private val _apiAnimes = MutableStateFlow<List<AnimeElement>>(emptyList())

    val favoriteAnimes = repository.getAllFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val animeList: StateFlow<List<AnimeElement>> = combine(_apiAnimes, favoriteAnimes) { apiList, favList ->
        val favIds = favList.map { it.id }.toSet()
        apiList.map { anime ->
            anime.copy(isFavorite = favIds.contains(anime.id))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        loadAnimesFromApi()
    }

    private fun loadAnimesFromApi() {
        viewModelScope.launch {
            _apiAnimes.value = repository.getTopAnimesFromApi()
        }
    }

    fun getFilteredList(): List<AnimeElement> {
        val currentList = animeList.value
        return if (searchQuery.isEmpty()) {
            currentList
        } else {
            currentList.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                        it.description.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    // --- LÓGICA DE FAVORITOS ---
    fun onFavoriteIconClicked(anime: AnimeElement) {
        if (anime.isFavorite) {
            Toast.makeText(getApplication(), "El elemento ya está guardado como favorito", Toast.LENGTH_SHORT).show()
        } else {
            viewModelScope.launch {
                repository.saveFavorite(anime)
                Toast.makeText(getApplication(), "Añadido a favoritos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun removeFavorite(animeId: Int) {
        viewModelScope.launch {
            repository.removeFavorite(animeId)
        }
    }

    // --- LÓGICA DE COMENTARIOS ---
    fun getCommentsForAnime(animeId: Int) = repository.getComments(animeId)

    fun addComment(animeId: Int, text: String) {
        viewModelScope.launch {
            val currentAuthor = username.value
            repository.addComment(animeId, currentAuthor, text)
        }
    }

    // AÑADIDO: Lanzar el borrado del comentario en Room
    fun removeComment(commentId: Int) {
        viewModelScope.launch {
            repository.removeComment(commentId)
        }
    }
}