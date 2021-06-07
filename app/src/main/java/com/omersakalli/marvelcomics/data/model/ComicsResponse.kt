package com.omersakalli.marvelcomics.data.model


import com.google.gson.annotations.SerializedName

data class ComicsResponse(
    @SerializedName("data")
    val data: Data,
)