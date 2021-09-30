package com.example.destination.database

import androidx.room.*
import com.example.destination.model.movie.MovieResult
import com.example.destination.models.show.ShowResult
@Dao
interface ShowDAO {

    @Query("SELECT * FROM SHOWS")
    fun getAll(): List<ShowResult>

    @Insert
    fun insertNote(showResult: ShowResult)

    @Delete
    fun deleteNote(showResult: ShowResult)

    @Update
    fun updateNote(showResult: ShowResult)

    @Query("SELECT * FROM shows WHERE id = :id")
    fun getShowById(id: Int): ShowResult?
}