package com.omersakalli.marvelcomics.data.model


import com.google.gson.annotations.SerializedName

//TODO: too crowded, delete unused parts to simplify
data class Result(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("title")
    val title: String
)