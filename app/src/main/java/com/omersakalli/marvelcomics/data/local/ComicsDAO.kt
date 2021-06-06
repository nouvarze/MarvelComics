package com.omersakalli.marvelcomics.data.local

import androidx.room.*
import com.omersakalli.marvelcomics.ui.model.Comic

@Dao
interface ComicsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(comics: List<Comic>)

    @Delete
    fun delete(comic: Comic)

    @Query("DELETE FROM comic")
    fun deleteAll()

    @Query("SELECT * FROM comic")
    fun getAll(): List<Comic>
}