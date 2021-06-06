package com.omersakalli.marvelcomics.data.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("extension")
    val extension: String,
    @SerializedName("path")
    val path: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(extension)
        parcel.writeString(path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Thumbnail> {
        override fun createFromParcel(parcel: Parcel): Thumbnail {
            return Thumbnail(parcel)
        }

        override fun newArray(size: Int): Array<Thumbnail?> {
            return arrayOfNulls(size)
        }
    }
}