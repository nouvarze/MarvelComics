package com.omersakalli.marvelcomics.data.remote

import android.util.Log
import com.omersakalli.marvelcomics.ui.model.Comic
import com.omersakalli.marvelcomics.utils.LoggingTags
import com.omersakalli.marvelcomics.data.Result
import okhttp3.ResponseBody
import java.net.ConnectException
import java.net.UnknownHostException

class RemoteDataSource(private val marvelService: MarvelService) {

    suspend fun getComics(): Result<List<Comic>> {
        try {
            val response = marvelService.getComics()
            return if (response.isSuccessful) {
                response.body()?.let {
                    return Result(Comic.convertResponseToComicsList(it), true)
                } ?: return Result(null,false, "Response body is empty")
            } else {
                Log.d(
                    LoggingTags.NETWORK_SERVICE,
                    "Error while fetching comics: \n" + (response.errorBody() as ResponseBody)
                )
                return Result(null, false, "Couldn't fetch response from server:\n"+response.errorBody())
            }
        }
        catch (e: ConnectException){
            Log.d(
                LoggingTags.NETWORK_SERVICE,
                "Error while fetching comics ConnectException: \n",
                e
            )
            return Result(null, false, "Check your internet connection")
        }
        catch (e: UnknownHostException){
            Log.d(
                LoggingTags.NETWORK_SERVICE,
                "Error while fetching comics UnknownHostException: \n",
                e
            )
            return Result(null, false, "Check your internet connection")
        }

    }
}