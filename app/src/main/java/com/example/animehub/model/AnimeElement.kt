package com.example.animehub.model

data class AnimeElement(
    val id: Int,
    val name: String,
    val rank: Int,
    val score: Double,
    val photo: String,
    val description: String,
    val isFavorite: Boolean = false
)