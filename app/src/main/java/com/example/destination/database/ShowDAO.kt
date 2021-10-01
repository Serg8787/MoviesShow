package com.example.destination.database

import androidx.room.*
import com.example.destination.model.movie.MovieResult
import com.example.destination.models.show.ShowResult
@Dao
interface ShowDAO {

    @Query("SELECT * FROM SHOWS")
    fun getAll(): List<ShowResult>

    @Insert
    fun insertShow(showResult: ShowResult)

    @Delete
    fun deleteShow(showResult: ShowResult)

    @Update
    fun updateShow(showResult: ShowResult)

    @Query("SELECT * FROM shows WHERE id = :id")
    fun getShowById(id: Int): ShowResult?
}