package com.omersakalli.marvelcomics.data

import com.omersakalli.marvelcomics.data.Repository
import com.omersakalli.marvelcomics.data.local.LocalDataSource
import com.omersakalli.marvelcomics.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): Repository =
        Repository(localDataSource,remoteDataSource)
}