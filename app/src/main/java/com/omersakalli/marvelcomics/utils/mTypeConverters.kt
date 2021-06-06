package com.omersakalli.marvelcomics.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.omersakalli.marvelcomics.data.model.Thumbnail

class mTypeConverters {
    @TypeConverter
    fun thumbnailToString(thumbnail: Thumbnail): String = Gson().toJson(thumbnail)

    @TypeConverter
    fun stringToThumbnail(string: String): Thumbnail =
        Gson().fromJson(string, Thumbnail::class.java)
}