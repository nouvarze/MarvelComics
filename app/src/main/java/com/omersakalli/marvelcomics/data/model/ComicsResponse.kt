package com.omersakalli.marvelcomics.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ComicsResponse(
    @PrimaryKey
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val data: Data,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("status")
    val status: String
)