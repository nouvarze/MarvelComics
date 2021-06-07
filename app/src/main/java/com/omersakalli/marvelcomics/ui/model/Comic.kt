package com.omersakalli.marvelcomics.ui.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.omersakalli.marvelcomics.data.model.ComicsResponse
import com.omersakalli.marvelcomics.data.model.Thumbnail
import com.omersakalli.marvelcomics.utils.MTypeConverters

@Entity
@TypeConverters(MTypeConverters::class)
data class Comic(
    @PrimaryKey
    val id: Int?,
    val thumbnail: Thumbnail?,
    val title: String?,
    val description: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readParcelable(Thumbnail::class.java.classLoader),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeParcelable(thumbnail, flags)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comic> {
        override fun createFromParcel(parcel: Parcel): Comic {
            return Comic(parcel)
        }

        override fun newArray(size: Int): Array<Comic?> {
            return arrayOfNulls(size)
        }
        fun convertResponseToComicsList(response: ComicsResponse): ArrayList<Comic> {
            var comics: ArrayList<Comic> = ArrayList()
            response.data.results.forEach {
                comics.add(
                    Comic(
                        it.id,
                        it.thumbnail,
                        it.title,
                        it.description
                    )
                )
            }
            return comics
        }
    }
}