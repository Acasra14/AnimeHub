package com.example.animehub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_animes")
data class AnimeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val rank: Int,
    val score: Double,
    val photoUrl: String,
    val description: String
)