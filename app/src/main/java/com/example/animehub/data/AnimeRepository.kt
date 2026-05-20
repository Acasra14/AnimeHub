package com.example.animehub.data

import com.example.animehub.data.local.AnimeDao
import com.example.animehub.data.local.AnimeEntity
import com.example.animehub.data.local.CommentEntity
import com.example.animehub.data.remote.RetrofitClient
import com.example.animehub.model.AnimeElement
import kotlinx.coroutines.flow.Flow

class AnimeRepository(private val animeDao: AnimeDao) {

    // 1. Obtener datos de la API (Internet)
    suspend fun getTopAnimesFromApi(): List<AnimeElement> {
        return try {
            val response = RetrofitClient.apiService.getTopAnimes()
            response.data.map { dto ->
                AnimeElement(
                    id = dto.malId,
                    name = dto.title,
                    rank = dto.rank ?: 0,
                    score = dto.score ?: 0.0,
                    photo = dto.images.jpg.imageUrl,
                    description = dto.synopsis ?: "Sin descripción."
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    // 2. Operaciones de Favoritos (Room)
    fun getAllFavorites(): Flow<List<AnimeEntity>> = animeDao.getAllFavorites()

    suspend fun saveFavorite(anime: AnimeElement): Long {
        val entity = AnimeEntity(
            id = anime.id,
            name = anime.name,
            rank = anime.rank,
            score = anime.score,
            photoUrl = anime.photo,
            description = anime.description
        )
        return animeDao.insertFavorite(entity)
    }

    suspend fun removeFavorite(animeId: Int) {
        animeDao.deleteFavorite(animeId)
    }

    // 3. Operaciones de Comentarios (Room)
    fun getComments(animeId: Int): Flow<List<CommentEntity>> = animeDao.getCommentsForAnime(animeId)

    suspend fun addComment(animeId: Int, author: String, text: String) {
        animeDao.insertComment(CommentEntity(animeId = animeId, authorName = author, text = text))
    }

    // AÑADIDO: Función para borrar el comentario de la base de datos
    suspend fun removeComment(commentId: Int) {
        animeDao.deleteComment(commentId)
    }
}