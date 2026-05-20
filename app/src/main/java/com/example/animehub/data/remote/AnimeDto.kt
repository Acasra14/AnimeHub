package com.example.animehub.data.remote

import com.google.gson.annotations.SerializedName

// La API de Jikan envuelve la lista de animes dentro de un objeto llamado "data"
data class AnimeResponse(
    @SerializedName("data") val data: List<AnimeDto>
)

// Esto es lo que leemos de cada anime que viene de Internet
data class AnimeDto(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("rank") val rank: Int?,
    @SerializedName("score") val score: Double?,
    @SerializedName("synopsis") val synopsis: String?,
    @SerializedName("images") val images: AnimeImages
)

data class AnimeImages(
    @SerializedName("jpg") val jpg: JpgImage
)

data class JpgImage(
    @SerializedName("image_url") val imageUrl: String
)