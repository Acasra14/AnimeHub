package com.example.animehub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_comments")
data class CommentEntity(
    @PrimaryKey(autoGenerate = true) val commentId: Int = 0,
    val animeId: Int, // Para saber a qué anime pertenece este comentario
    val authorName: String, // Aquí guardaremos el nombre que sacamos del DataStore
    val text: String
)