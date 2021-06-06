package com.omersakalli.marvelcomics.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omersakalli.marvelcomics.ui.model.Comic

@Database(
    entities = [Comic::class],
    version = 1,
    exportSchema = false
)
abstract class ComicsDatabase:RoomDatabase() {
    abstract fun comicsDao(): ComicsDAO
}