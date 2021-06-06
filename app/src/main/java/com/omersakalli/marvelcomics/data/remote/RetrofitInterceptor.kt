package com.omersakalli.marvelcomics.data.remote

import com.omersakalli.marvelcomics.utils.Constants
import com.omersakalli.marvelcomics.utils.EncryptionUtils
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.text.DateFormat
import java.util.*

class RetrofitInterceptor : Interceptor {
    //As every call to the service requires the API key we add an interceptor so we don't have to write it down every time we make an API call
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val ts = DateFormat.getTimeInstance().format(Date())
        proceed(
            request()
                .newBuilder()
                .url(request().url
                    .addQuery("apikey", Constants.PUBLIC_API_KEY)
                    .addQuery("ts", ts)
                    .addQuery("hash", EncryptionUtils.md5Hash(ts+Constants.PRIVATE_API_KEY+Constants.PUBLIC_API_KEY))
                )
                .build()
        )
    }

    private fun HttpUrl.addQuery(queryName: String, queryValue: String): HttpUrl =
        this.newBuilder().addQueryParameter(queryName, queryValue).build()
}