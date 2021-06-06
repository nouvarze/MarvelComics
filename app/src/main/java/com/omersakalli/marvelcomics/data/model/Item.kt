package com.omersakalli.marvelcomics.data.model


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("role")
    val role: String?,
    @SerializedName("type")
    val type: String?
)