package com.omersakalli.marvelcomics.data.remote

import com.omersakalli.marvelcomics.data.model.ComicDetailsResponse
import com.omersakalli.marvelcomics.data.model.ComicsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("v1/public/comics")
    suspend fun getComics():Response<ComicsResponse>

    @GET("v1/public/comics")
    suspend fun getComicDetails(@Query("comicId") comicId: Int):Response<ComicDetailsResponse>
}