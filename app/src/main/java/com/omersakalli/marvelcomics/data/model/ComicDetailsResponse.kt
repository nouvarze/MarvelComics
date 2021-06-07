package com.omersakalli.marvelcomics.data.model


import com.google.gson.annotations.SerializedName

//Currently not used
data class ComicDetailsResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("data")
    val data: Data,
    @SerializedName("etag")
    val eTag: String,
    @SerializedName("status")
    val status: String
)