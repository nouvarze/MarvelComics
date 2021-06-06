package com.omersakalli.marvelcomics.data.remote

import com.omersakalli.marvelcomics.data.remote.MarvelService
import com.omersakalli.marvelcomics.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(marvelService: MarvelService):RemoteDataSource{
        return RemoteDataSource(marvelService)
    }
}