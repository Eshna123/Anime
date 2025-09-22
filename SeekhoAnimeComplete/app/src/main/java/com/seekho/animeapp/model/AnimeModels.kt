package com.seekho.animeapp.model

import com.google.gson.annotations.SerializedName

data class TopAnimeResponse(@SerializedName("data") val data: List<AnimeDto> = emptyList())

data class AnimeDto(
    val mal_id: Int,
    val title: String?,
    val images: Map<String, ImageDto>?,
    val episodes: Int?,
    val score: Double?
)

data class ImageDto(val image_url: String?)

data class AnimeDetailResponse(val data: AnimeDetailDto)
data class AnimeDetailDto(
    val mal_id: Int,
    val title: String?,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val trailer: TrailerDto?,
    val images: Map<String, ImageDto>?,
    val genres: List<GenreDto>?,
    val characters: CharactersWrapper?
)
data class TrailerDto(val youtube_id: String?, val url: String?)
data class GenreDto(val mal_id: Int, val name: String?)
data class CharactersWrapper(val data: List<CharacterDto>?)
data class CharacterDto(val character: PersonName?, val role: String?)
data class PersonName(val name: String?)
