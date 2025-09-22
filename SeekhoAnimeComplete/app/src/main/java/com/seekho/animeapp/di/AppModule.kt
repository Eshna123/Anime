package com.seekho.animeapp.di

import android.content.Context
import androidx.room.Room
import com.seekho.animeapp.db.AnimeDatabase
import com.seekho.animeapp.network.ApiService
import com.seekho.animeapp.network.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService = RetrofitProvider.api

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AnimeDatabase =
        Room.databaseBuilder(context, AnimeDatabase::class.java, "anime_db").build()

}
