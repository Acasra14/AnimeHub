package com.example.animehub.data.remote

import retrofit2.http.GET

interface JikanApiService {
    // Le pedimos a la API que nos traiga el "Top Anime"
    @GET("top/anime")
    suspend fun getTopAnimes(): AnimeResponse
}