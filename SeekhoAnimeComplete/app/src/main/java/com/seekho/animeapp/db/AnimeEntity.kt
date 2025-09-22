package com.seekho.animeapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey val id: Int,
    val title: String?,
    val episodes: Int?,
    val score: Double?,
    @ColumnInfo(name = "image_url") val imageUrl: String?
)
