package com.seekho.animeapp.network

import com.seekho.animeapp.model.AnimeDetailResponse
import com.seekho.animeapp.model.TopAnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("top/anime")
    suspend fun getTopAnime(): Response<TopAnimeResponse>

    @GET("anime/{id}")
    suspend fun getAnimeDetail(@Path("id") id: Int): Response<AnimeDetailResponse>
}
