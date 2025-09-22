package com.seekho.animeapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimeEntity::class], version = 1)
abstract class AnimeDatabase: RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}
