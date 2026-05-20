package com.example.animehub.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    // --- FAVORITOS ---
    @Query("SELECT * FROM favorite_animes")
    fun getAllFavorites(): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(anime: AnimeEntity): Long

    @Query("DELETE FROM favorite_animes WHERE id = :animeId")
    suspend fun deleteFavorite(animeId: Int)

    // --- COMENTARIOS ---
    @Query("SELECT * FROM anime_comments WHERE animeId = :animeId")
    fun getCommentsForAnime(animeId: Int): Flow<List<CommentEntity>>

    @Insert
    suspend fun insertComment(comment: CommentEntity)

    // AÑADIDO: Consulta para borrar un comentario por su ID
    @Query("DELETE FROM anime_comments WHERE commentId = :commentId")
    suspend fun deleteComment(commentId: Int)
}