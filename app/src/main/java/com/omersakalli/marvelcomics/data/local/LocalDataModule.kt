package com.omersakalli.marvelcomics.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Singleton
    @Provides
    fun provideComicsDataBase(@ApplicationContext context: Context): ComicsDatabase =
        Room.databaseBuilder(context, ComicsDatabase::class.java, "comicsclient").build()

    @Singleton
    @Provides
    fun provideComicsDAO(comicsDatabase: ComicsDatabase): ComicsDAO = comicsDatabase.comicsDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(comicsDao: ComicsDAO): LocalDataSource = LocalDataSource(comicsDao)
}