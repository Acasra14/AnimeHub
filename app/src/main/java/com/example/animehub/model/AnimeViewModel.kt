package com.example.animehub.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.animehub.data.Datasource
import com.example.animehub.model.AnimeElement

class AnimeViewModel : ViewModel() {
    private val _animeList = Datasource.getAnimeElements().toMutableStateList()
    val animeList: List<AnimeElement> = _animeList

    var searchQuery by mutableStateOf("")

    var isLoggedIn by mutableStateOf(false)

    fun toggleFavorite(elementName: String) {
        val index = _animeList.indexOfFirst { it.name == elementName }
        if (index != -1) {
            _animeList[index] = _animeList[index].copy(isFavorite = !_animeList[index].isFavorite)
        }
    }

    fun getFilteredList(): List<AnimeElement> {
        return if (searchQuery.isEmpty()) {
            _animeList
        } else {
            _animeList.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                        it.description.contains(searchQuery, ignoreCase = true)
            }
        }
    }
}