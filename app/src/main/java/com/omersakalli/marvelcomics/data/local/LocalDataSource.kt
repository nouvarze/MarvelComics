package com.omersakalli.marvelcomics.data.local

import com.omersakalli.marvelcomics.data.Result
import com.omersakalli.marvelcomics.ui.model.Comic

class LocalDataSource(private val comicsDao:ComicsDAO) {
    fun getComics(): Result<List<Comic>> {
        val comics = comicsDao.getAll()
        return if(!comics.isNullOrEmpty()){
            Result(comics,true)
        } else Result(null,false, "No comics found")
    }

    fun saveComics(comics: List<Comic>){
        comicsDao.insertAll(comics)
    }

    fun deleteAllComics(){
        comicsDao.deleteAll()
    }

    fun deleteComic(comic: Comic){
        comicsDao.delete(comic)
    }
}