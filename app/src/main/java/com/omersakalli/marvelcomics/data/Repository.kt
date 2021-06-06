package com.omersakalli.marvelcomics.data

import com.omersakalli.marvelcomics.data.local.LocalDataSource
import com.omersakalli.marvelcomics.data.remote.RemoteDataSource
import com.omersakalli.marvelcomics.ui.model.Comic

class Repository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {

    suspend fun getComics(): Result<List<Comic>> {
        val dbResponse = localDataSource.getComics()
        if(!dbResponse.isSuccessful){
            return getComicsFromNetwork()
        }
        else return dbResponse
    }

    suspend fun getComicsFromNetwork(): Result<List<Comic>> {
        val remoteResponse = remoteDataSource.getComics()
        if(remoteResponse.isSuccessful){
            localDataSource.deleteAllComics()
            remoteResponse.variable?.let {
                localDataSource.saveComics(it)
            }
        }
        return remoteResponse
    }
}