// Archivo: com/example/animehub/viewmodel/AnimeViewModel.kt
package com.example.animehub.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.animehub.data.Datasource
import com.example.animehub.model.AnimeElement

class AnimeViewModel : ViewModel() {

    private val _animeList = Datasource.getAnimeElements().toMutableStateList()
    val animeList: List<AnimeElement> = _animeList

    fun toggleFavorite(elementName: String) {
        val index = _animeList.indexOfFirst { it.name == elementName }
        if (index != -1) {
            val currentElement = _animeList[index]
            _animeList[index] = currentElement.copy(isFavorite = !currentElement.isFavorite)
        }
    }

    fun removeFavorite(elementName: String) {
        val index = _animeList.indexOfFirst { it.name == elementName }
        if (index != -1) {
            _animeList[index] = _animeList[index].copy(isFavorite = false)
        }
    }
}