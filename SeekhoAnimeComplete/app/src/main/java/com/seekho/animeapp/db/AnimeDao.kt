package com.seekho.animeapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime ORDER BY score DESC")
    suspend fun getAll(): List<AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<AnimeEntity>)

    @Query("DELETE FROM anime")
    suspend fun clearAll()
}
