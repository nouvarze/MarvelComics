package com.omersakalli.marvelcomics.data.model


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price")
    val price: Int,
    @SerializedName("type")
    val type: String
)