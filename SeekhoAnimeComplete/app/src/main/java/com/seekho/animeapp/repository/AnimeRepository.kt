package com.seekho.animeapp.repository

import com.seekho.animeapp.db.AnimeDatabase
import com.seekho.animeapp.db.AnimeEntity
import com.seekho.animeapp.model.AnimeDetailResponse
import com.seekho.animeapp.model.AnimeDto
import com.seekho.animeapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeRepository(private val api: ApiService, private val db: AnimeDatabase) {
    private val dao = db.animeDao()

    suspend fun getTopAnime(): Result<List<AnimeEntity>> = withContext(Dispatchers.IO) {
        try {
            val cached = dao.getAll()
            if (cached.isNotEmpty()) return@withContext Result.success(cached)
            val resp = api.getTopAnime()
            if (resp.isSuccessful) {
                val list = resp.body()?.data ?: emptyList()
                val entities = list.map { toEntity(it) }
                dao.clearAll()
                dao.insertAll(entities)
                Result.success(entities)
            } else Result.failure(Exception("API ${'$'}{resp.code()}"))
        } catch (e: Exception) {
            val cached = dao.getAll()
            if (cached.isNotEmpty()) Result.success(cached) else Result.failure(e)
        }
    }

    suspend fun refreshTopAnime(): Result<List<AnimeEntity>> = withContext(Dispatchers.IO) {
        try {
            val resp = api.getTopAnime()
            if (resp.isSuccessful) {
                val list = resp.body()?.data ?: emptyList()
                val entities = list.map { toEntity(it) }
                dao.clearAll()
                dao.insertAll(entities)
                Result.success(entities)
            } else Result.failure(Exception("API ${'$'}{resp.code()}"))
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun getAnimeDetail(id: Int): Result<AnimeDetailResponse> = withContext(Dispatchers.IO) {
        try {
            val resp = api.getAnimeDetail(id)
            if (resp.isSuccessful) resp.body()?.let { Result.success(it) } ?: Result.failure(Exception("Empty"))
            else Result.failure(Exception("API ${'$'}{resp.code()}"))
        } catch (e: Exception) { Result.failure(e) }
    }

    private fun toEntity(dto: AnimeDto): AnimeEntity {
        val img = dto.images?.values?.firstOrNull()?.image_url
        return AnimeEntity(dto.mal_id, dto.title, dto.episodes, dto.score, img)
    }
}
